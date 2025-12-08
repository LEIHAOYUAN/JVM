package com.lei.jvm.google.retail.geohash;

import com.google.api.client.util.Lists;
import com.google.api.client.util.Maps;
import com.google.cloud.retail.v2.CustomAttribute;
import com.google.cloud.retail.v2.LocalInventory;
import com.google.cloud.retail.v2.Product;
import com.lei.jvm.google.retail.utils.ListUtil;
import com.lei.jvm.google.retail.utils.MapUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author ryan
 */
public final class ProductGeoHashConvertor {

    public static Map<String, Double> buildSimpleGeoHashMap() {
        Map<String, Double> geohashMap = com.google.common.collect.Maps.newHashMap();
        geohashMap.put("dpdffnm", 1.0000001D);
        //geohashMap.put("aaaaaaa", 2.0000002D);
//        geohashMap.put("bbbbbbb", 3.0000003D);
//        geohashMap.put("ccccccc", 4.0000004D);
        geohashMap.put("ddddddd", 5.0000004D);
        geohashMap.put("eeeeeee", 6.0000004D);
        geohashMap.put("fffffff", 7.0000004D);
        return geohashMap;
    }

    public static Map<String, Double> buildExistedLocalInventoryMap(Product product) {
        if (product == null || ListUtil.isEmpty(product.getLocalInventoriesList())) {
            return Maps.newHashMap();
        }
        Map<String, Double> existedGeohashDistanceMap = Maps.newHashMap();
        for (LocalInventory localInventory : product.getLocalInventoriesList()) {
            if (localInventory == null || StringUtils.isBlank(localInventory.getPlaceId())) {
                continue;
            }
            CustomAttribute customAttribute = localInventory.getAttributesMap().get(ProductConstant.PRODUCT_LOCAL_INVENTORY_DISTANCE);
            if (customAttribute == null || ListUtil.isEmpty(customAttribute.getNumbersList())) {
                existedGeohashDistanceMap.put(localInventory.getPlaceId(), null);
            } else {
                existedGeohashDistanceMap.put(localInventory.getPlaceId(), customAttribute.getNumbersList().getFirst());
            }
        }
        return existedGeohashDistanceMap;
    }

    public static DiffGeoHashRecord calcLocalInventoryMap(Map<String, Double> existedMap, Map<String, Double> param) {
        if (MapUtil.isEmpty(existedMap)) {
            return new DiffGeoHashRecord(Lists.newArrayList(), param);
        }
        if (MapUtil.isEmpty(param)) {
            return new DiffGeoHashRecord(existedMap.keySet().stream().toList(), Maps.newHashMap());
        }
        Map<String, Double> addGeoHashDistanceMap = param.entrySet().stream().filter(entry -> {
            Double existValue = existedMap.get(entry.getKey());
            return !Objects.equals(existValue, entry.getValue());
        }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        List<String> removedPlaceIds = existedMap.keySet().stream().filter(i -> !param.containsKey(i)).collect(Collectors.toList());
        return new DiffGeoHashRecord(removedPlaceIds, addGeoHashDistanceMap);
    }

    public record DiffGeoHashRecord(List<String> removedPlaceIds, Map<String, Double> addGeoHashDistanceMap) {
    }
}
