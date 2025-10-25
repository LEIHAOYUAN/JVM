package com.base;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ryan
 */
public class SetUtil {
    public static boolean isEmpty(Set<?> set) {
        return set == null || set.isEmpty();
    }

    public static boolean isNotEmpty(Set<?> set) {
        return !isEmpty(set);
    }

    public static <T> Set<T> newHashSet() {
        return new HashSet<>();
    }

    public static <T> Set<T> newConcurrentHashSet() {
        return ConcurrentHashMap.newKeySet();
    }

    public static <T> Set<T> newHashSetWithExpectedSize(int size) {
        int capacity;
        if (size < 3) {
            capacity = size + 1;
        } else {
            capacity = (int) (size / 0.75f + 1);
        }
        return new HashSet<>(capacity);
    }
}
