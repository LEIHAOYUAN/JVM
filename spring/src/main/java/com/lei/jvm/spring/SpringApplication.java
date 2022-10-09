package com.lei.jvm.spring;

import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *  职能描述：启动类
 *  @author leihaoyuan
 *  @version 2022/10/9 11:13
 */
@SpringBootApplication
public class SpringApplication {
    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(SpringApplication.class, args);
    }
}
