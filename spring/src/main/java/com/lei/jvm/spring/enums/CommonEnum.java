package com.lei.jvm.spring.enums;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *  职能描述：开启MTCache开关
 *  @author leihaoyuan
 *  @version 2022/9/22 14:34
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CommonEnum {

    /**
     * 是否开启
     */
    boolean enabled() default false;

    int size() default 0;
}
