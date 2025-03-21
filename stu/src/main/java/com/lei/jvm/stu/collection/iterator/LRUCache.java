package com.lei.jvm.stu.collection.iterator;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author leihaoyuan
 * @date 2025年03月21日 16:30
 * @description
 */
@Slf4j
public class LRUCache<K, V> extends LinkedHashMap<K, V> {

    public static void main(String[] args) {
        LRUCache<String, String> cache = new LRUCache<>(3);
        cache.put("1", "1");
        cache.put("2", "2");
        cache.put("3", "3");
        String s = cache.get("1");
        cache.put("4", "4");

        System.out.println(cache);
    }

    public LRUCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    /**
     * 容量大小
     */
    private int capacity;

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        // 链表长度达到上限，触发删除链表最早未使用元素
        return this.size() > this.capacity;
    }


}
