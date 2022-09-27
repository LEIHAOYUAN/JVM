package com.lei.annotation;

import lombok.Getter;

/**
 *  职能描述：缓存值序列化方式枚举
 *  @author leihaoyuan
 *  @version 2022/9/14 14:42
 */
@Getter
public enum CacheDataSerializationType {
    STRING(1, "字符串序列化"),
    JSON(2, "JSON序列化"),
    HESSIAN(3, "Hessian序列化");

    /**
     * 编号
     */
    private int code;
    /**
     * 名称
     */
    private String name;

    CacheDataSerializationType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static CacheDataSerializationType valueOf(int code) {
        for (CacheDataSerializationType em : CacheDataSerializationType.values()) {
            if (em.code == code) {
                return em;
            }
        }
        return null;
    }


}
