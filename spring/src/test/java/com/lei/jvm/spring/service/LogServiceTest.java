package com.lei.jvm.spring.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *  职能描述：
 *  @author leihaoyuan
 *  @version 2022/10/9 13:05
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class LogServiceTest {

    @Autowired
    private LogService logService;

    @BeforeClass
    public static void init() {
        log.info("单元测试开始执行........");
    }

    @Test
    public void testLog() {
        logService.log();
    }

    @AfterClass
    public static void close() {
        log.info("单元测试执行完毕.......");
    }


}
