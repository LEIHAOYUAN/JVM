package com.lei.jvm.stu.thread;

import com.lei.jvm.utils.base.utils.thread.ThreadPoolUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author leihaoyuan
 * @Date 2021/11/1 11:15
 * @Version 1.0
 * @Description
 */
@Slf4j
public class ExceptionTest {

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolUtil.execute(new Runnable() {
            @Override
            public void run() {
                handler1();
                handler2();
                handler3();
            }
        });
        log.info("主线程执行完毕............");
    }


    private static void handler1() {
        log.info("方法1开始执行");
    }

    private static void handler2() {
        log.info("方法2开始执行");
        try{
            int a = 1/0;
        }catch (Exception ex){
            log.error("方法2异常信息：{}",ex.getMessage(),ex);
        }
    }

    private static void handler3() {
        log.info("方法3开始执行");
    }


}
