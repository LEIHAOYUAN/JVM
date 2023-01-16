package com.lei.jvm.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *  职能描述：启动类
 *  @author leihaoyuan
 *  @version 2022/10/9 11:13
 */
@Slf4j
@EnableMultiLang(extraResources = {"aaa.yml", "bbb.yml"})
@SpringBootApplication
public class MyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
}
