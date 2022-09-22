package com.lei.temp;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 *  职能描述：缓存全局配置开关
 *  @author leihaoyuan
 *  @version 2022/9/22 14:34
 */
@Data
public class CacheConfigDomain implements Serializable {

    private static final long serialVersionUID = 5451751682593461511L;

    /**
     *  redis缓存全局控制开关（通过配置中心覆盖默认值）
     *  默认：开启
     */
    private Boolean redisCacheSwitch;

    /**
     *  本地缓存全局控制开关（通过配置中心覆盖默认值）
     *  默认：关闭
     */
    private Boolean localCacheSwitch;

    /**
     * 默认缓存失效时间，单位：秒（通过配置中心覆盖默认值）
     * 默认值：30分钟
     */
    private Long defaultExpireTime;

    /**
     *  本地缓存配置项
     */
    private Map<String, LocalCacheConfigDomain> localConfigMap;
}
