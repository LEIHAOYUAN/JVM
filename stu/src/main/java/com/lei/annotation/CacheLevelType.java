package com.lei.annotation;

import lombok.Getter;

/**
 *  职能描述：缓存级别枚举
 *  @author leihaoyuan
 *  @version 2022/9/14 14:35
 */
@Getter
public enum CacheLevelType {

    NONE(0, "不缓存"),
    LOCAL(1, "本地缓存"),
    REDIS(2, "redis缓存"),
    COMPOSITE(99, "混合缓存");

    /**
     * 编号
     */
    private int code;
    /**
     * 名称
     */
    private String name;

    CacheLevelType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static CacheLevelType valueOf(int code) {
        for (CacheLevelType em : CacheLevelType.values()) {
            if (em.code == code) {
                return em;
            }
        }
        return null;
    }

}
