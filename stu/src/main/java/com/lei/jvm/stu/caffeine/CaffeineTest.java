package com.lei.jvm.stu.caffeine;

import com.alibaba.fastjson.JSON;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalCause;
import com.github.benmanes.caffeine.cache.RemovalListener;
import com.lei.jvm.stu.clone.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 *  职能描述：caffeine测试
 *  @author leihaoyuan
 *  @version 2022/9/15 11:12
 */
@Slf4j
public class CaffeineTest {

    public static void main(String[] args) {
        new CaffeineTest().testCache();
    }

    private void testCache() {
        String item = "AAAAAAAA";
        Cache<Object, People> cache = buildCache();
        for (int i = 1; i < 5000; i++) {
            cache.put(i, new People(item + i));
        }
        People o1 = cache.getIfPresent(1);
        log.info("查询缓存结果1：{}", JSON.toJSONString(o1));
        o1.setName("XXXXX");
        Object o2 = cache.getIfPresent(1);
        log.info("查询缓存结果2：{}", JSON.toJSONString(o2));
    }


    private static Cache buildCache() {
        return Caffeine.newBuilder()
                //设置缓存的 Entries 个数最多不超过1000个
                .maximumSize(5000).removalListener(new RemovalListener<Object, Object>() {
                    @Override
                    public void onRemoval(@Nullable Object key, @Nullable Object value, @NonNull RemovalCause removalCause) {
                        log.info("key={}已被移除", key);
                    }
                }).build();
    }

    @Data
    @AllArgsConstructor
    public  class People{
        private String name;
    }


}
