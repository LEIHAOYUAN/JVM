package com.lei.jvm.spring;

import com.alibaba.fastjson.JSON;
import com.lei.jvm.spring.enums.CommonEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *  职能描述：
 *  @author leihaoyuan
 *  @version 2022/11/8 17:23
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class AnnotationUtilsTest {


    @Autowired
    private ApplicationContext context;

    @Test
    public void testAnnotation() {
        Map<String, Object> beansWithAnnotation = context.getBeansWithAnnotation(CommonEnum.class);
        for (Map.Entry<String, Object> stringObjectEntry : beansWithAnnotation.entrySet()) {
            log.info("注解类名：{}", stringObjectEntry.getKey());
        }

        CommonEnum annotation = AnnotationUtils.findAnnotation(SpringBootApplication.class, CommonEnum.class);
        log.info("注解属性={}", JSON.toJSONString(annotation.enabled()));
    }

    @Test
    public void testFindAnnotation() {
        Map<String, Object> beansWithAnnotation = context.getBeansWithAnnotation(CommonEnum.class);
        log.info("获取到指定注解的beans={}", beansWithAnnotation);
        for (Map.Entry<String, Object> entry : beansWithAnnotation.entrySet()) {
            CommonEnum annotation = context.findAnnotationOnBean(entry.getKey(), CommonEnum.class);
            if (null != annotation) {
                log.info("注解值[enabled]={},[size]={}", annotation.enabled(), annotation.size());
                break;
            }
        }
    }

    @Test
    public void testScanAnnotation() {
        String BASE_PACKAGE = "com.example.demo";
        String RESOURCE_PATTERN = "/**/*.class";

        Map<String, Class> handlerMap = new HashMap<String, Class>();

        //spring工具类，可以获取指定路径下的全部类
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        try {
            String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + ClassUtils.convertClassNameToResourcePath(BASE_PACKAGE) + RESOURCE_PATTERN;
            Resource[] resources = resourcePatternResolver.getResources(pattern);
            //MetadataReader 的工厂类
            MetadataReaderFactory readerfactory = new CachingMetadataReaderFactory(resourcePatternResolver);
            for (Resource resource : resources) {
                //用于读取类信息
                MetadataReader reader = readerfactory.getMetadataReader(resource);
                //扫描到的class
                String classname = reader.getClassMetadata().getClassName();
                Class<?> clazz = Class.forName(classname);
                //判断是否有指定主解
                CommonEnum anno = clazz.getAnnotation(CommonEnum.class);
                if (anno != null) {
                    //将注解中的类型值作为key，对应的类作为 value
                    handlerMap.put(classname, clazz);
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            log.error("异常={}", ex.getMessage(), ex);
        }
    }


}
