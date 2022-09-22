package com.lei.jvm.volatilet;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * volatile关键字测试验证
 */
@Slf4j
public class VolatitleTest {
    //类变量
    final static int max = 5;
    volatile static int init_value = 0;

    public static void main(String args[]) {
        //启动一个线程，当发现local_value与init_value不同时，则输出init_value被修改的值
        new Thread(() -> {
            int localValue = init_value;
            while (localValue < max) {
                if (init_value != localValue) {
                    log.info("The init_value is update ot {}", init_value);
                    //对localValue进行重新赋值
                    localValue = init_value;
                }
            }
        }, "Reader").start();
        //启动updater线程，主要用于对init_value的修改，当local_value=5的时候退出生命周期
        new Thread(() -> {
            int localValue = init_value;
            while (localValue < max) {
                //修改init_value
                log.info("The init_value will be changed to {}", ++localValue);
                init_value = localValue;
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Updater").start();
    }
}