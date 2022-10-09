package com.lei.jvm.stu.annotation;

import lombok.Getter;

/**
 *  职能描述：缓存值数据结构枚举
 *  @author leihaoyuan
 *  @version 2022/9/14 14:43
 */
@Getter
public enum CacheDataStructureType {
    STRING(1, "字符串类型"),
    LIST(2, "列表类型"),
    HASH(3, "哈希表类型"),
    SET(4, "无序集合类型"),
    ZSET(5, "有序集合类型");
    /**
     * 编号
     */
    private int code;
    /**
     * 名称
     */
    private String name;

    CacheDataStructureType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static CacheDataStructureType valueOf(int code) {
        for (CacheDataStructureType em : CacheDataStructureType.values()) {
            if (em.code == code) {
                return em;
            }
        }
        return null;
    }


}
