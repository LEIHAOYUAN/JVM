package com.lei.jvm.stu.annotation;


import java.lang.annotation.*;


/**
 *  职能描述：使用缓存注解
 *  @author leihaoyuan
 *  @version 2022/9/22 14:34
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MTCache {

    /**
     * 定义domain名称
     */
    String domain();

    /**
     * 动态缓存key（EL表达式）
     */
    String[] keys() default {};

    /**
     * 过期时间（单位：秒）
     * 默认值：30分钟
     */
    long ttl() default 30 * 60;

    /**
     * 缓存数据类型
     */
    Class<?> clazz();

    /**
     *  是否续约（重置过期时间）
     *  默认：不续约
     */
    boolean renewal() default false;

    /**
     *  缓存存储类型
     * @see CacheLevelType
     *  默认值：redis缓存
     */
    CacheLevelType cacheLevelType() default CacheLevelType.REDIS;


    /**
     * 缓存值序列化方式
     * 默认值：字符串类型
     */
    CacheDataSerializationType serialiType() default CacheDataSerializationType.STRING;
}
