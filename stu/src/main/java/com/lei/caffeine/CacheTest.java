package com.lei.caffeine;

import com.alibaba.fastjson.JSON;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;

/**
 *  职能描述：caffeine测试
 *  @author leihaoyuan
 *  @version 2022/9/15 11:12
 */
@Slf4j
public class CacheTest {

    public static void main(String[] args) {
        String item = "AAAAAAAA";
        Cache<Object, Object> cache = buildCache();
        for (int i = 1; i <= 5000; i++) {
            cache.put(i, item + i);
        }
        log.info("查询缓存结果：{}", JSON.toJSONString(cache.getIfPresent(5000)));
        cache.invalidate(5000-10);
        log.info("查询缓存结果：{}", JSON.toJSONString(cache.getIfPresent(5000-10)));
        log.info("查询缓存结果：{}", JSON.toJSONString(cache.getIfPresent(5000-11)));
    }


    private static Cache buildCache() {
        return Caffeine
                .newBuilder()
                //设置缓存的 Entries 个数最多不超过1000个
                .maximumSize(5000)
                .build();
    }


}
