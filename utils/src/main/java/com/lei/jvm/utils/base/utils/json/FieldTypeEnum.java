package com.lei.jvm.utils.base.utils.json;

import lombok.Getter;

import java.math.BigDecimal;

/**
 *  职能描述：字段类型枚举
 *  @author leihaoyuan
 *  @version 2023/1/11 13:20
 */
public enum FieldTypeEnum {

    NUM_INT("int", "整型"),
    NUM_LONG("long", "长整型"),
    NUM_DECIMAL("decimal", "浮点型"),
    STRING("string", "字符串"),
    BOOLEAN("boolean", "布尔型"),
    ARRAY("array", "数组"),
    DATE("date", "日期时间"),
    ;

    @Getter
    private String code;
    @Getter
    private String label;

    FieldTypeEnum(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public static Class formatClass(FieldTypeEnum fieldTypeEnum) {
        if (null == fieldTypeEnum) {
            return String.class;
        }
        switch (fieldTypeEnum) {
            case NUM_INT:
                return Integer.class;
            case NUM_LONG:
                return Long.class;
            case NUM_DECIMAL:
                return BigDecimal.class;
            default:
                return String.class;
        }
    }


}
