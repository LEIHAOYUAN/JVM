package com.lei.jvm.stu.temp;

import com.lei.jvm.utils.base.consts.NumberConstants;
import com.google.common.collect.Maps;
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

    private CacheConfigDomain() {
    }

    private static final CacheConfigDomain INSTANCE = new CacheConfigDomain();

    public static CacheConfigDomain getInstance() {
        // 设置默认属性
        INSTANCE.setGlobalCacheSwitch(true);
        INSTANCE.setRedisCacheSwitch(true);
        INSTANCE.setLocalCacheSwitch(false);
        INSTANCE.setPermitsPerSecond(NumberConstants.INT_NUM_NEGATIVE_1);
        INSTANCE.setDefaultRedisExpireTime(NumberConstants.LONG_NUM_1800);
        INSTANCE.setLocalConfigMap(Maps.newConcurrentMap());
        return INSTANCE;
    }

    /**
     * 缓存全局配置开关（通过配置中心覆盖默认值）
     * 默认：开启
     */
    private Boolean globalCacheSwitch;
    /**
     *  redis缓存全局控制开关（通过配置中心覆盖默认值）
     *  默认：开启
     */
    private Boolean redisCacheSwitch;

    /**
     * 默认redis缓存失效时间，单位：秒（通过配置中心覆盖默认值）
     * 默认值：30分钟
     */
    private Long defaultRedisExpireTime;

    /**
     *  缓存击穿限流阈值
     * （每秒钟通过限流阈值：基于令牌桶算法）
     *  默认值：-1（大于0表示开启，否则表示不开启）
     */
    private Integer permitsPerSecond;

    /**
     *  本地缓存全局控制开关（通过配置中心覆盖默认值）
     *  默认：关闭
     */
    private Boolean localCacheSwitch;

    /**
     *  本地缓存配置项（通过私有化配置）
     */
    private Map<String, LocalCacheConfigDomain> localConfigMap;
}
