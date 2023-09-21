package com.lei.jvm.stu.annotation;


import java.lang.annotation.*;


/**
 *  职能描述：使用缓存注解
 *  @author leihaoyuan
 *  @version 2022/9/22 14:34
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MultiLangProp {

    /**
     * 定义domain名称
     */
    String domain();
}
