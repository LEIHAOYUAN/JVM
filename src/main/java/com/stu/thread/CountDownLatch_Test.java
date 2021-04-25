package com.stu.thread;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @Author leihaoyuan
 * @Date 2021/4/25 10:10
 * @Version 1.0
 * @Description
 */
@Slf4j
public class CountDownLatch_Test {


    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    Thread.sleep(new Random().nextInt(20));
                    log.info("子线程：{}-----执行", Thread.currentThread().getName());
                    latch.countDown();
                }
            }).start();
        }
        latch.await();
        log.info("主线程执行");
    }

}
