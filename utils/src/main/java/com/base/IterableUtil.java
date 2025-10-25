package com.base;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class IterableUtil {
    public static boolean isNullOrEmpty(Iterable<?> iterable) {
        if (iterable == null) {
            return true;
        } else if (iterable instanceof Collection && ((Collection) iterable).isEmpty()) {
            return true;
        } else {
            return !iterable.iterator().hasNext();
        }
    }

    public static int sizeOf(Iterable<?> iterable) {
        Objects.requireNonNull(iterable, "Iterable must not be null");
        return iterable instanceof Collection ? ((Collection) iterable).size() : Math.toIntExact(StreamUtil.stream(iterable).count());
    }

    public static <T> List<T> nonNullElementsIn(Iterable<? extends T> i) {
        return isNullOrEmpty(i) ? Collections.emptyList() : (List) StreamUtil.stream(i).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public static <T> T[] toArray(Iterable<? extends T> iterable) {
        return (T[]) (iterable == null ? null : ListUtil.newArrayList(iterable).toArray());
    }

    public static <T> T[] toArray(Iterable<? extends T> iterable, Class<T> type) {
        if (iterable == null) {
            return null;
        } else {
            Collection<? extends T> collection = toCollection(iterable);
            T[] array = (T[]) newArray(type, collection.size());
            return (T[]) collection.toArray(array);
        }
    }

    public static <T> Collection<T> toCollection(Iterable<T> iterable) {
        return (Collection<T>) (iterable instanceof Collection ? (Collection) iterable : ListUtil.newArrayList(iterable));
    }

    @SafeVarargs
    public static <T> Iterable<T> iterable(T... elements) {
        if (elements == null) {
            return null;
        } else {
            List<T> list = ListUtil.newArrayList();
            Collections.addAll(list, elements);
            return list;
        }
    }

    @SafeVarargs
    public static <T> Iterator<T> iterator(T... elements) {
        return elements == null ? null : iterable(elements).iterator();
    }

    private static <T> T[] newArray(Class<T> type, int length) {
        return (T[]) ((Object[]) Array.newInstance(type, length));
    }

    private IterableUtil() {
    }
}