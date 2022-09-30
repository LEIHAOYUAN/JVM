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
        String json = formatConfigJson();
        transferConfigJson(json);
    }


    private static String formatConfigJson() {
        LocalCacheConfigDomain localDomain = new LocalCacheConfigDomain();
        localDomain.setMaximumSize(1000L);
        localDomain.setExpireAfterWrite(1800L);
        Map<String, LocalCacheConfigDomain> localConfigMap = Maps.newConcurrentMap();
        localConfigMap.put("study-map", localDomain);

        CacheConfigDomain domain = CacheConfigDomain.getInstance();
        domain.setLocalConfigMap(localConfigMap);


        String json = JSON.toJSONString(domain);
        log.info("配置项格式化：{}", json);
        return json;
    }

    private static void transferConfigJson(String param) {
        CacheConfigDomain cacheConfigDomain = JSON.parseObject(param, CacheConfigDomain.class);
        log.info("反序列化结束");
    }


}
