package com.base;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author ryan
 */
public class MapUtil {
    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    public static <K, V> List<Map<K, V>> toBatchMap(Map<K, V> map, int batchSize) {
        if (batchSize <= 0) {
            throw new IllegalArgumentException("batchSize must be greater than 0, batchSize=" + batchSize);
        }
        if (isEmpty(map)) {
            return Lists.newArrayList();
        }
        List<List<Map.Entry<K, V>>> entryLists = ListUtil.toBatch(new ArrayList<>(map.entrySet()), batchSize);
        List<Map<K, V>> result = new ArrayList<>(entryLists.size());
        for (List<Map.Entry<K, V>> entryList : entryLists) {
            Map<K, V> batchMap = new LinkedHashMap<>(entryList.size());
            for (Map.Entry<K, V> entry : entryList) {
                batchMap.put(entry.getKey(), entry.getValue());
            }
            result.add(batchMap);
        }
        return result;
    }

    public static <T, V> Map<T, V> newHashMap() {
        return new HashMap<>();
    }

    public static <T, V> ConcurrentMap<T, V> newConcurrentHashMap() {
        return new ConcurrentHashMap<>();
    }

    public static <T, V> Map<T, V> newLinkedHashMap() {
        return new LinkedHashMap<>();
    }

    public static <T, V> Map<T, V> newLinkedHashMapWithExpectedSize(int size) {
        return new LinkedHashMap<>(capacity(size));
    }

    public static <T, V> Map<T, V> newHashMapWithExpectedSize(int size) {
        return new HashMap<>(capacity(size));
    }

    private static int capacity(int size) {
        int capacity;
        if (size < 3) {
            capacity = size + 1;
        } else {
            capacity = (int) (size / 0.75f + 1);
        }
        return capacity;
    }
}
