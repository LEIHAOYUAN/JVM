package com.lei.jvm.stu.design.singleton;

import lombok.Data;

import java.io.Serializable;

/**
 *  职能描述：缓存全局配置开关
 *  @author leihaoyuan
 *  @version 2022/9/22 14:34
 */
@Data
public class CacheConfigDTO implements Serializable {

    public static CacheConfigDTO getInstance() {
        return INSTANCE;
    }

    private static final CacheConfigDTO INSTANCE = new CacheConfigDTO();

    private CacheConfigDTO() {
    }

    private static final long serialVersionUID = 5451751682593461511L;

    /**
     *  redis缓存全局控制开关（通过配置中心覆盖默认值）
     *  默认：开启
     */
    private boolean redisCacheSwitch = true;

    /**
     *  本地缓存全局控制开关（通过配置中心覆盖默认值）
     *  默认：关闭
     */
    private boolean localCacheSwitch = false;

    /**
     * 默认缓存失效时间，单位：秒（通过配置中心覆盖默认值）
     * 默认值：30分钟
     */
    private long defaultExpireTime = 1800;


}
