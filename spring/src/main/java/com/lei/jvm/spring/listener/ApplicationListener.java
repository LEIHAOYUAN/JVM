package com.lei.jvm.spring.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *  职能描述：启动监听器
 *  @author leihaoyuan
 *  @version 2022/11/3 9:11
 */
@Slf4j
@Component
public class ApplicationListener implements CommandLineRunner {


    @Override
    public void run(String... args) throws Exception {
        log.info("监听容器启动成功......");
    }


}
