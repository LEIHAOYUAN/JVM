package com.lei.jvm.spring;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *  职能描述：测试jar包资源
 *  @author leihaoyuan
 *  @version 2023/2/13 10:27
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class JarTest {

    @Test
    public void testJarSource(){
        String jarPath = MyApplication.class
                .getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .getPath();
        log.info("获取JAR路径={}",jarPath);
    }



}
