package com.lei.jvm.utils.base.pair;

public class Pair<K, V> {
    private final K key;
    private final V value;

    // 私有构造函数，防止外部直接实例化
    private Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public static <K, V> Pair<K, V> of(K key, V value) {
        return new Pair<>(key, value);
    }
}