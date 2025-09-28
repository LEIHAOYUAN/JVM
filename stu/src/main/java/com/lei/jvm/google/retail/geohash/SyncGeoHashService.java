package com.lei.jvm.google.retail.geohash;

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
import com.lei.jvm.google.retail.ProductClient;
import com.lei.jvm.google.retail.build.CommonBuilder;
import com.lei.jvm.google.retail.utils.ListUtil;
import lombok.extern.slf4j.Slf4j;
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
                ProductClient.doImport(productId);
            }
            doSyncLocalInventoryHandler(product, ProductGeoHashConvertor.buildSimpleGeoHashMap());
        } catch (Exception ex) {
            log.error("异常={}", ex.getMessage(), ex);
        }
    }

    private static void doSyncLocalInventoryHandler(Product product, Map<String, Double> geohashMap) {
        if (product == null) {
            throw new IllegalArgumentException("product is null");
        }
        try {
            ProductServiceClient productServiceClient = ProductServiceClient.create();

            Map<String, Double> existedMap = ProductGeoHashConvertor.buildExistedLocalInventoryMap(product);
            ProductGeoHashConvertor.DiffGeoHashRecord diffGeoHashRecord = ProductGeoHashConvertor.calcLocalInventoryMap(existedMap, geohashMap);
            // remove local inventories
            if (ListUtil.isNotEmpty(diffGeoHashRecord.removedPlaceIds())) {
                RemoveLocalInventoriesRequest request = buildRemoveLocalInventoriesRequest(product.getId(), diffGeoHashRecord.removedPlaceIds());
                OperationFuture<RemoveLocalInventoriesResponse, RemoveLocalInventoriesMetadata> future = productServiceClient.removeLocalInventoriesAsync(request);
                future.addListener(() -> {
                    try {
                        future.get();
                    } catch (Exception ex) {
                        log.error("异常={}", ex.getMessage(), ex);
                    }
                }, ProductConstant.MONITOR_EXECUTOR);
            }
            // add local inventories
            if (cn.hutool.core.map.MapUtil.isNotEmpty(diffGeoHashRecord.addGeoHashDistanceMap())) {
                AddLocalInventoriesRequest request = buildAddLocalInventoriesRequest(product.getId(), diffGeoHashRecord.addGeoHashDistanceMap());
                OperationFuture<AddLocalInventoriesResponse, AddLocalInventoriesMetadata> future = productServiceClient.addLocalInventoriesAsync(request);
                future.addListener(() -> {
                    try {
                        future.get();
                    } catch (Exception ex) {
                        log.error("异常={}", ex.getMessage(), ex);
                    }
                }, ProductConstant.MONITOR_EXECUTOR);
            }
        } catch (Exception ex) {
            log.error("异常={}", ex.getMessage(), ex);
        }
    }

    private static RemoveLocalInventoriesRequest buildRemoveLocalInventoriesRequest(String productId, List<String> placeIds) {
        return RemoveLocalInventoriesRequest.newBuilder()
            .setProduct(CommonBuilder.buildProduct(productId))
            .addAllPlaceIds(placeIds)
            .setRemoveTime(CommonBuilder.buildUTCTimestamp())
            .setAllowMissing(true).build();
    }

    private static AddLocalInventoriesRequest buildAddLocalInventoriesRequest(String productId, Map<String, Double> geohashMap) {
        List<LocalInventory> localInventories = Lists.newArrayList();
        if (geohashMap != null && !geohashMap.isEmpty()) {
            for (Map.Entry<String, Double> entry : geohashMap.entrySet()) {
                LocalInventory localInventory = LocalInventory.newBuilder()
                    .setPlaceId(entry.getKey())
                    .putAttributes(ProductConstant.PRODUCT_LOCAL_INVENTORY_DISTANCE, CustomAttribute.newBuilder().addNumbers(entry.getValue()).build()).build();
                localInventories.add(localInventory);
            }
        }
        return AddLocalInventoriesRequest.newBuilder().setProduct(CommonBuilder.buildProduct(productId))
            .addAllLocalInventories(localInventories)
            .setAddTime(CommonBuilder.buildUTCTimestamp()).setAllowMissing(true)
            .build();
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
