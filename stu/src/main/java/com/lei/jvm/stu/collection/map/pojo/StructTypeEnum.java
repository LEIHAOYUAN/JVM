package com.lei.jvm.stu.collection.map.pojo;

import lombok.Getter;

/**
 *  职能描述：结构体类型
 *  @author leihaoyuan
 *  @version 2023/2/3 10:50
 */
public enum StructTypeEnum {

    /**
     * 基本类型
     */
    BASE("Base"),
    /**
     * 数组
     */
    ARRAY("Array"),
    /**
     * 对象
     */
    OBJECT("Object");

    @Getter
    private final String code;

    StructTypeEnum(String code) {
        this.code = code;
    }

}
