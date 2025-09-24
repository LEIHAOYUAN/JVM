package com.lei.jvm.google.retail.geohash;

import com.google.api.client.util.Lists;
import com.google.api.client.util.Maps;
import com.google.cloud.retail.v2.CustomAttribute;
import com.google.cloud.retail.v2.LocalInventory;
import com.google.cloud.retail.v2.Product;
import com.lei.jvm.google.retail.utils.ListUtil;
import com.lei.jvm.google.retail.utils.MapUtil;
import org.apache.logging.log4j.util.Strings;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author ryan
 */
public final class ProductGeoHashConvertor {

    public static Map<String, Double> buildExistedLocalInventoryMap(Product product) {
        if (product == null || ListUtil.isEmpty(product.getLocalInventoriesList())) {
            return Maps.newHashMap();
        }
        Map<String, Double> existedGeohashDistanceMap = Maps.newHashMap();
        for (LocalInventory localInventory : product.getLocalInventoriesList()) {
            if (localInventory == null || Strings.isBlank(localInventory.getPlaceId())) {
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
