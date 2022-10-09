package com.lei.jvm.stu.annotation;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

/**
 *  职能描述：注解API
 *  @author leihaoyuan
 *  @version 2022/9/27 15:42
 */
@Slf4j
public class AnnotationTest {


    public static void main(String[] args) {
        testDefaultValue();
    }


    /**
     * 获取方法指定注解
     */
    public static void testDefaultValue() {
        Method[] methods = AnnotationDemo.class.getMethods();
        for (Method method : methods) {
            MTCache annotation = method.getAnnotation(MTCache.class);
            if (null != annotation) {
                log.info("【domain】={}", annotation.domain());
                log.info("【keys】={}", JSON.toJSONString(annotation.keys()));
                log.info("【ttl】={}", annotation.ttl());
                log.info("【clazz】={}", annotation.clazz());
                log.info("【renewal】={}", annotation.renewal());
                log.info("【cacheLevelType】={}", annotation.cacheLevelType().getName());
                log.info("【serialiType】={}", annotation.serialiType().getName());
            }
        }
    }

}
