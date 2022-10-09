package com.lei.jvm.stu.shutdownhook;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;

/**
 * @Author leihaoyuan
 * @Date 2021/4/26 9:59
 * @Version 1.0
 * @Description jvm shutdownhook钩子程序
 */
@Slf4j
public class ShutDownHookTest {


    public static void main(String[] args) {
        new ShutDownHookTest().hook();
        log.info("main方法执行.....");
    }


    @PostConstruct
    public void hook(){
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("hook方法执行.....");
            }
        }));
    }

}
