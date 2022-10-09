package com.lei.jvm.stu.thread.interrupt;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author leihaoyuan
 * @Date 2021/11/8 11:00
 * @Version 1.0
 * @Description 使用volatile控制线程
 */
@Slf4j
public class VolatileInterruptTest {

    /**
     * 控制线程变量
     */
    private static volatile boolean NOTIFY_FLAG = true;

    public static void main(String[] args) throws InterruptedException {
        log.info("main方法执行start.......");
        InterruptThread thread = new InterruptThread();
        thread.start();
        // 主线程休眠
        Thread.sleep(1000);
        // 更新变量
        NOTIFY_FLAG = false;
        // 等待thread子线程结束后继续执行main线程
        thread.join();
        log.info("main方法执行end.........");
    }


    private static class InterruptThread extends Thread {
        @Override
        public void run() {
            while (NOTIFY_FLAG) {
                log.info("线程正在执行。。。。。。。。。");
            }
            log.info("被中断!!!!!!");
        }
    }


}
