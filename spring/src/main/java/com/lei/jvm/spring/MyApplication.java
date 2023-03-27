package com.lei.jvm.spring;

import com.lei.jvm.spring.enums.CommonEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *  职能描述：启动类
 *  @author leihaoyuan
 *  @version 2022/10/9 11:13
 */
@Slf4j
@CommonEnum(enabled = true, size = Integer.MAX_VALUE)
@SpringBootApplication
public class MyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
}
