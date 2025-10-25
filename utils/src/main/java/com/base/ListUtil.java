package com.base;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

public class ListUtil {
    public static <T> List<List<T>> toBatch(List<T> list, int batchSize) {
        if (batchSize <= 0)
            throw new IllegalArgumentException("batchSize must be greater than 0, batchSize=" + batchSize);

        if (list == null || list.isEmpty()) return List.of();
        return IntStream.range(0, (list.size() + batchSize - 1) / batchSize)
            .mapToObj(i -> list.subList(
                i * batchSize,
                Math.min((i + 1) * batchSize, list.size())
            )).toList();
    }

    public static <T, R> List<R> mapToImmutable(List<T> originalList, Function<T, R> apply) {
        if (originalList == null) return null;
        return originalList.stream().map(apply).toList();
    }

    public static boolean isEmpty(List<?> list) {
        return list == null || list.isEmpty();
    }

    public static boolean isNotEmpty(List<?> list) {
        return !isEmpty(list);
    }

    public static List<String> truncate(List<String> itemIds, int max) {
        if (isEmpty(itemIds)) return Lists.newArrayList();
        if (itemIds.size() <= max) return itemIds;
        return itemIds.stream().limit(max).toList();
    }
}
