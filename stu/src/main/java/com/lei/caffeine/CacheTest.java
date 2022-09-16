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
        Cache cache = buildCache();
        for (int i = 0; i < 50000000; i++) {
            cache.put(i,item+i);
        }
        Object ifPresent = cache.getIfPresent(50000000-1);
        log.info("查询缓存结果：{}", JSON.toJSONString(ifPresent));

    }


    private static Cache buildCache(){
        return Caffeine
                .newBuilder()
                //设置缓存的 Entries 个数最多不超过1000个
                .maximumSize(10)
                .build();
    }





}
