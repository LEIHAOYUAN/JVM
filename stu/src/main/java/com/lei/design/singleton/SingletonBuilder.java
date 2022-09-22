package com.lei.design.singleton;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 *  职能描述：
 *  @author leihaoyuan
 *  @version 2022/9/22 15:42
 */
@Slf4j
public class SingletonBuilder {

    public static void main(String[] args) {
        CacheConfigDTO instance = CacheConfigDTO.getInstance();
        log.info("单例对象默认值：{}", JSON.toJSONString(instance));

        String json = "{\"defaultExpireTime\":2000,\"localCacheSwitch\":false,\"redisCacheSwitch\":true}";

        CacheConfigDTO cacheConfigDTO = JSON.parseObject(json, CacheConfigDTO.class);
        log.info("单例对象反序列化结果：{}",cacheConfigDTO);
    }


}
