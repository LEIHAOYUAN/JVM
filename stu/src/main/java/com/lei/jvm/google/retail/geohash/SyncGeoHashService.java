package com.lei.jvm.google.retail.geohash;

import cn.hutool.core.date.StopWatch;
import com.google.api.gax.longrunning.OperationFuture;
import com.google.cloud.retail.v2.AddLocalInventoriesMetadata;
import com.google.cloud.retail.v2.AddLocalInventoriesRequest;
import com.google.cloud.retail.v2.AddLocalInventoriesResponse;
import com.google.cloud.retail.v2.CustomAttribute;
import com.google.cloud.retail.v2.LocalInventory;
import com.google.cloud.retail.v2.Product;
import com.google.cloud.retail.v2.ProductServiceClient;
import com.google.cloud.retail.v2.RemoveLocalInventoriesMetadata;
import com.google.cloud.retail.v2.RemoveLocalInventoriesRequest;
import com.google.cloud.retail.v2.RemoveLocalInventoriesResponse;
import com.google.common.collect.Lists;
import com.google.longrunning.Operation;
import com.lei.jvm.google.retail.ProductClient;
import com.lei.jvm.google.retail.build.CommonBuilder;
import com.lei.jvm.google.retail.builder.GeoHashMapGenerator;
import com.lei.jvm.google.retail.utils.ListUtil;
import com.lei.jvm.google.retail.utils.MapUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Triple;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

/**
 * @author ryan
 */
@Slf4j
public class SyncGeoHashService {

    public static Triple<String, Boolean, Double> checkGeoHash(String name, String geoHash) {
        Product product = ProductClient.doGetByName(name);
        if (product == null) {
            throw new IllegalArgumentException("product not exist");
        }

        Map<String, Double> geoHashMap = ProductGeoHashConvertor.buildExistedLocalInventoryMap(product);
        String brandCategory = parseBrandCategory(product);
        return Triple.of(brandCategory, geoHashMap.containsKey(geoHash), geoHashMap.get(geoHash));
    }

    public static void doSyncLocalInventory(String productId) {
        try {
            Product product = ProductClient.doGetById(productId);
            if (product == null) {
                ProductClient.doImportWithMetadata(productId);
            }
            Map<String, Double> existedMap = ProductGeoHashConvertor.buildExistedLocalInventoryMap(product);
            Map<String, Double> newMap = ProductGeoHashConvertor.buildSimpleGeoHashMap();
            ProductGeoHashConvertor.DiffGeoHashRecord diffGeoHashRecord = ProductGeoHashConvertor.calcLocalInventoryMap(existedMap, newMap);
            removeLocalInventoryHandler(productId, diffGeoHashRecord);
            addLocalInventoryHandler(productId, diffGeoHashRecord);
        } catch (Exception ex) {
            log.error("异常={}", ex.getMessage(), ex);
        }
    }

    public static void doSyncLocalInventoryLimit(String productId) {
        try {
            ProductClient.doImportWithMetadata(productId);
            long total = 0;
            while (true) {
                Map<String, Double> geoHashMap = GeoHashMapGenerator.generateUniqueMap();
                ProductGeoHashConvertor.DiffGeoHashRecord diffGeoHashRecord = new ProductGeoHashConvertor.DiffGeoHashRecord(Lists.newArrayList(), geoHashMap);
                addLocalInventoryHandler(productId, diffGeoHashRecord);
                total = total + geoHashMap.size();
                if (total >= 10_0000) {
                    log.info("同步完成-总数={}", total);
                    break;
                }
            }
        } catch (Exception ex) {
            log.error("异常={}", ex.getMessage(), ex);
        }
    }

    private static void removeLocalInventoryHandler(String productId, ProductGeoHashConvertor.DiffGeoHashRecord diffGeoHashRecord) {
        if (ListUtil.isEmpty(diffGeoHashRecord.removedPlaceIds())) {
            return;
        }
        try {
            RemoveLocalInventoriesRequest request = RemoveLocalInventoriesRequest.newBuilder()
                .setProduct(CommonBuilder.buildProduct(productId))
                .addAllPlaceIds(diffGeoHashRecord.removedPlaceIds())
                .setRemoveTime(CommonBuilder.buildUTCTimestamp())
                .setAllowMissing(true).build();
            ProductServiceClient productServiceClient = ProductServiceClient.create();
            OperationFuture<RemoveLocalInventoriesResponse, RemoveLocalInventoriesMetadata> future = productServiceClient.removeLocalInventoriesAsync(request);
            future.addListener(() -> {
                StopWatch stopWatch = StopWatch.create("RemoveLocalInventory-Monitor");
                stopWatch.start();
                try {
                    Operation operation = productServiceClient.getOperationsClient().getOperation(future.getName());
                    if (!operation.getDone() || StringUtils.isNotBlank(operation.getError().getMessage())) {
                        log.info("RemoveLocalInventory-失败");
                    }
                } catch (Exception ex) {
                    log.error("doRemoveLocalInventory_fail={}", ex.getMessage(), ex);
                }
                stopWatch.stop();
                log.info("RemoveLocalInventor-总耗时=[{}]秒", stopWatch.getTotalTimeSeconds());
            }, ProductConstant.MONITOR_EXECUTOR);
        } catch (Exception ex) {
            log.error("doRemoveLocalInventory_error={}", ex.getMessage(), ex);
        }
    }

    private static void addLocalInventoryHandler(String productId, ProductGeoHashConvertor.DiffGeoHashRecord diffGeoHashRecord) {
        if (MapUtil.isEmpty(diffGeoHashRecord.addGeoHashDistanceMap())) {
            return;
        }
        List<LocalInventory> localInventories = Lists.newArrayList();
        for (Map.Entry<String, Double> entry : diffGeoHashRecord.addGeoHashDistanceMap().entrySet()) {
            LocalInventory localInventory = LocalInventory.newBuilder().setPlaceId(entry.getKey()).putAttributes(ProductConstant.PRODUCT_LOCAL_INVENTORY_DISTANCE, CustomAttribute.newBuilder().addNumbers(entry.getValue()).build()).build();
            localInventories.add(localInventory);
        }
        try {
            AddLocalInventoriesRequest request = AddLocalInventoriesRequest.newBuilder().setProduct(CommonBuilder.buildProduct(productId))
                .addAllLocalInventories(localInventories).setAddTime(CommonBuilder.buildUTCTimestamp()).setAllowMissing(true).build();
            ProductServiceClient productServiceClient = ProductServiceClient.create();
            OperationFuture<AddLocalInventoriesResponse, AddLocalInventoriesMetadata> future = productServiceClient.addLocalInventoriesAsync(request);
            future.addListener(() -> {
                StopWatch stopWatch = StopWatch.create("AddLocalInventory-Monitor");
                stopWatch.start();
                try {
                    Operation operation = productServiceClient.getOperationsClient().getOperation(future.getName());
                    if (operation.getDone() && StringUtils.isBlank(operation.getError().getMessage())) {
                        log.info("AddLocalInventory-success");
                    }
                } catch (Exception ex) {
                    log.error("doAddLocalInventory_fail={}", ex.getMessage(), ex);
                }
                stopWatch.stop();
                log.info("AddLocalInventory-总耗时=[{}]秒", stopWatch.getTotalTimeSeconds());
            }, ProductConstant.MONITOR_EXECUTOR);
        } catch (Exception ex) {
            log.error("doAddLocalInventory_error={}", ex.getMessage(), ex);
        }
    }

    private static String parseBrandCategory(Product product) {
        CustomAttribute restaurantBrandCategory = product.getAttributesMap().get(ProductConstant.PRODUCT_RESTAURANT_BRAND_CATEGORY);
        if (restaurantBrandCategory == null || ListUtil.isEmpty(restaurantBrandCategory.getTextList())) {
            return "";
        }
        return restaurantBrandCategory.getTextList().getFirst();
    }

    private static String loadJson() {
        String fileName = "C:\\工作文档\\BaiduSyncdisk\\google\\data\\geohash.json";
        try (InputStream is = Files.newInputStream(Path.of(fileName))) {
            return new String(is.readAllBytes(), StandardCharsets.UTF_8);
        } catch (Exception ex) {
            log.error("load file error={}", ex.getMessage(), ex);
            return null;
        }
    }

}
