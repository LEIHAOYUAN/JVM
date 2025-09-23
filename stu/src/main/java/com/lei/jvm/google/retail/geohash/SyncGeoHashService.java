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
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.util.concurrent.RateLimiter;
import com.lei.jvm.google.retail.ProductClient;
import com.lei.jvm.google.retail.build.CommonBuilder;
import com.lei.jvm.google.retail.utils.ListUtil;
import com.lei.jvm.google.retail.utils.MapUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @author ryan
 */
@Slf4j
public class SyncGeoHashService {
    private static final ExecutorService MONITOR_EXECUTOR = Executors.newVirtualThreadPerTaskExecutor();
    private static final RateLimiter LIMITER = RateLimiter.create(60 / 60);

    public static void syncLocalInventory(String productId) {
        try {
            Product product = ProductClient.doGet(productId);
            if (product == null) {
                ProductClient.doImportWithFuture(productId);
            }
//            Map<String, Set<String>> geohashMap = JSON.parseObject(loadJson(), new TypeReference<>() {
//            });
            Map<String, Set<String>> geohashMap = buildSimpleGeoHashMap();
            doSyncRecLocalInventory(product, geohashMap);
        } catch (Exception ex) {
            log.error("异常={}", ex.getMessage(), ex);
        }
    }

    private static Map<String, Set<String>> buildSimpleGeoHashMap() {
        Map<String, Set<String>> geohashMap = Maps.newHashMap();
        geohashMap.put("dpdffnm", Set.of("0.0009"));
        return geohashMap;
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

    private static void doSyncRecLocalInventory(Product product, Map<String, Set<String>> geohashMap) {
        try {
            ProductServiceClient productServiceClient = ProductServiceClient.create();
            if (product == null) {
                return;
            }
            Map<String, Set<String>> existedMap = buildExistedLocalInventoryMap(product);
            DiffGeoHashBatchRecord diffGeoHashRecord = calcLocalInventoryMap(existedMap, geohashMap);
            // remove local inventories
            if (ListUtil.isNotEmpty(diffGeoHashRecord.batchRemovedPlaceIds)) {
                for (List<String> batchRemovedPlaceId : diffGeoHashRecord.batchRemovedPlaceIds) {
                    if (ListUtil.isNotEmpty(batchRemovedPlaceId)) {
                        RemoveLocalInventoriesRequest request = buildRemoveLocalInventoriesRequest(product.getId(), batchRemovedPlaceId);
                        OperationFuture<RemoveLocalInventoriesResponse, RemoveLocalInventoriesMetadata> future = productServiceClient.removeLocalInventoriesAsync(request);
                        future.addListener(() -> {
                            try {
                                future.get();
                            } catch (Exception ex) {
                                log.error("异常={}", ex.getMessage(), ex);
                            }
                        }, MONITOR_EXECUTOR);
                    }
                }
            }
            // add local inventories
            if (ListUtil.isNotEmpty(diffGeoHashRecord.batchAddGeoHashMaps)) {
                for (Map<String, Set<String>> addGeoHashMap : diffGeoHashRecord.batchAddGeoHashMaps) {
                    if (MapUtil.isNotEmpty(addGeoHashMap)) {
                        AddLocalInventoriesRequest request = buildAddLocalInventoriesRequest(product.getId(), addGeoHashMap);
                        OperationFuture<AddLocalInventoriesResponse, AddLocalInventoriesMetadata> future = productServiceClient.addLocalInventoriesAsync(request);
                        future.addListener(() -> {
                            try {
                                future.get();
                            } catch (Exception ex) {
                                log.error("异常={}", ex.getMessage(), ex);
                            }
                        }, MONITOR_EXECUTOR);
                    }
                }
            }
        } catch (Exception ex) {
            log.error("异常={}", ex.getMessage(), ex);
        }
    }


    public static RemoveLocalInventoriesRequest buildRemoveLocalInventoriesRequest(String productId, List<String> placeIds) {
        return RemoveLocalInventoriesRequest.newBuilder()
            .setProduct(CommonBuilder.buildProduct(productId))
            .addAllPlaceIds(placeIds)
            .setRemoveTime(CommonBuilder.buildUTCTimestamp())
            .setAllowMissing(true)
            .build();
    }

    public static AddLocalInventoriesRequest buildAddLocalInventoriesRequest(String productId, Map<String, Set<String>> geohashMap) {
        List<LocalInventory> localInventories = Lists.newArrayList();
        if (geohashMap != null && !geohashMap.isEmpty()) {
            for (Map.Entry<String, Set<String>> entry : geohashMap.entrySet()) {
                LocalInventory localInventory = LocalInventory.newBuilder()
                    .setPlaceId(entry.getKey())
                    .putAttributes(ProductConstant.PRODUCT_LOCAL_INVENTORY_DISTANCE, CustomAttribute.newBuilder().addNumbers(0.56897).build()).build();
                localInventories.add(localInventory);
            }
        }
        return AddLocalInventoriesRequest.newBuilder()
            .setProduct(CommonBuilder.buildProduct(productId))
            .addAllLocalInventories(localInventories)
            .setAddTime(CommonBuilder.buildUTCTimestamp())
            .setAllowMissing(true)
            .build();
    }

    private static Map<String, Set<String>> buildExistedLocalInventoryMap(Product product) {
        if (product == null || ListUtil.isEmpty(product.getLocalInventoriesList())) {
            return Maps.newHashMap();
        }
        Map<String, Set<String>> existedGeohashMap = Maps.newHashMap();
        for (LocalInventory localInventory : product.getLocalInventoriesList()) {
            if (localInventory == null || Strings.isBlank(localInventory.getPlaceId())) {
                continue;
            }
            CustomAttribute customAttribute = localInventory.getAttributesMap().get(ProductConstant.PRODUCT_LOCAL_INVENTORY_DISTANCE);
            if (customAttribute == null || ListUtil.isEmpty(customAttribute.getTextList())) {
                existedGeohashMap.put(localInventory.getPlaceId(), Sets.newHashSet());
            } else {
                existedGeohashMap.put(localInventory.getPlaceId(), new HashSet<>(customAttribute.getTextList()));
            }
        }
        return existedGeohashMap;
    }

    private static DiffGeoHashBatchRecord calcLocalInventoryMap(Map<String, Set<String>> existedMap, Map<String, Set<String>> param) {
        if (MapUtil.isEmpty(existedMap)) {
            return new DiffGeoHashBatchRecord(Lists.newArrayList(), MapUtil.toBatchMap(param, ProductConstant.GEO_HASH_BATCH_LIMIT));
        }
        if (MapUtil.isEmpty(param)) {
            return new DiffGeoHashBatchRecord(ListUtil.toBatch(existedMap.keySet().stream().toList(), ProductConstant.GEO_HASH_BATCH_LIMIT), Lists.newArrayList());
        }
        Map<String, Set<String>> addPlaceMap = param.entrySet().stream().filter(entry -> {
            Set<String> existValue = existedMap.get(entry.getKey());
            return existValue == null || existValue.size() != entry.getValue().size() || !existValue.containsAll(entry.getValue());
        }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        List<String> deletePlaceIds = existedMap.keySet().stream().filter(i -> !param.containsKey(i)).collect(Collectors.toList());
        return new DiffGeoHashBatchRecord(ListUtil.toBatch(deletePlaceIds, ProductConstant.GEO_HASH_BATCH_LIMIT), MapUtil.toBatchMap(addPlaceMap, ProductConstant.GEO_HASH_BATCH_LIMIT));
    }

    public record DiffGeoHashBatchRecord(List<List<String>> batchRemovedPlaceIds,
                                         List<Map<String, Set<String>>> batchAddGeoHashMaps) {
    }


}
