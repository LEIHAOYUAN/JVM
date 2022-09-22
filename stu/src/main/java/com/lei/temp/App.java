package com.lei.temp;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 *  职能描述：
 *  @author leihaoyuan
 *  @version 2022/9/22 18:12
 */
@Slf4j
public class App {

    public static void main(String[] args) {
        formatConfigJson();
    }

    private static void formatConfigJson() {
        LocalCacheConfigDomain localDomain = new LocalCacheConfigDomain();
        localDomain.setMaximumSize(1000L);
        localDomain.setExpireAfterWrite(1800L);
        Map<String, LocalCacheConfigDomain> localConfigMap = Maps.newConcurrentMap();
        localConfigMap.put("study_map", localDomain);

        CacheConfigDomain domain = new CacheConfigDomain();
        domain.setDefaultExpireTime(1800L);
        domain.setLocalCacheSwitch(false);
        domain.setRedisCacheSwitch(true);
        domain.setLocalConfigMap(localConfigMap);

        log.info("配置项格式化：{}", JSON.toJSONString(domain));
    }

}
