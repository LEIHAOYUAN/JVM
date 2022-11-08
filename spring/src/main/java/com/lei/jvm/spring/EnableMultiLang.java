package com.lei.jvm.spring;


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
public @interface EnableMultiLang {

    /**
     * 额外的资源文件名列表（资源需要在resources/lang目录下）
     */
    String[] extraResources() default {};


}
