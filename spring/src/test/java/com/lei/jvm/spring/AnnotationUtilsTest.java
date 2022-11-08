package com.lei.jvm.spring;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.test.context.junit4.SpringRunner;

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
        Map<String, Object> beansWithAnnotation = context.getBeansWithAnnotation(EnableMultiLang.class);
        for (Map.Entry<String, Object> stringObjectEntry : beansWithAnnotation.entrySet()) {
            log.info("注解类名：{}", stringObjectEntry.getKey());
        }

        EnableMultiLang annotation = AnnotationUtils.findAnnotation(SpringBootApplication.class, EnableMultiLang.class);
        log.info("注解属性={}", JSON.toJSONString(annotation.extraResources()));
        // 获取自定义注解

    }


}
