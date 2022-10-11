package com.lei.jvm.stu.serialization.json;

import com.lei.jvm.stu.serialization.SerializationInfo;
import lombok.Getter;
import org.apache.poi.ss.formula.functions.T;

/**
 *  职能描述：缓存级别枚举
 *  @author leihaoyuan
 *  @version 2022/9/14 14:35
 */
@Getter
public enum ClassEnumType {

//    NONE(0, SerializationInfo.class.getClass().),
    ;

    /**
     * 编号
     */
    private int code;
    /**
     * 名称
     */
    private Class<T> clazz;

    ClassEnumType(int code, Class<T> clazz) {
        this.code = code;
        this.clazz = clazz;
    }


}
