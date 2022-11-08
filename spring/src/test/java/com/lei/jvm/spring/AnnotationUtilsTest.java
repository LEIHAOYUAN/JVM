package com.lei.jvm.spring;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *  职能描述：
 *  @author leihaoyuan
 *  @version 2022/11/8 17:23
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class AnnotationUtilsTest {

    @Test
    public void testAnnotation() {
        AnnotationUtils.VALUE.getBytes();
    }


}
