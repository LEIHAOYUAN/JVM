package com.lei.stu.thread.interrupt;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author leihaoyuan
 * @Date 2021/11/6 14:19
 * @Version 1.0
 * @Description
 */
@Slf4j
public class InterruptTest {

    public static void main(String[] args) throws InterruptedException {
        log.info("main方法执行start.......");
        InterruptThread thread = new InterruptThread();
        thread.start();
        // 主线程休眠
        Thread.sleep(2000);
        thread.interrupt();
        // 等待thread子线程结束后继续执行main线程
        thread.join();
        log.info("main方法执行end.........");
    }


    static class InterruptThread extends Thread {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                log.info("线程正在执行。。。。。。。。。");
            }
            log.info("被中断!!!!!!");
        }
    }


}
