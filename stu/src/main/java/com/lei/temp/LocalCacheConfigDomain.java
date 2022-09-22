package com.lei.temp;

import lombok.Data;

import java.io.Serializable;

/**
 * @description 缓存配置
 *  @author leihaoyuan
 *  @version 2022/9/22 14:34
 */
@Data
public class LocalCacheConfigDomain implements Serializable {

    private static final long serialVersionUID = -3676230732882798385L;
    /**
     * 缓存写后过期时间，单位(秒)
     */
    private Long expireAfterWrite;

    /**
     * 缓存最大容量
     */
    private Long maximumSize;
}