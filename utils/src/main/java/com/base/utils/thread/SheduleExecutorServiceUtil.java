package com.base.utils.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *  职能描述：SheduleExecutorService测试
 *  @author leihaoyuan
 *  @version 2022/7/27 15:05
 */
@Slf4j
public class SheduleExecutorServiceUtil {

    private final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    public static void main(String[] args) throws InterruptedException {
        SheduleExecutorServiceUtil app = new SheduleExecutorServiceUtil();
//        app.testScheduler();
        app.testScheduleAtFixDelay();
        Thread.sleep(10000);
        app.testScheduleAtFixDelay();
        Thread.currentThread().join();
    }


    public void testScheduler() {
        scheduledExecutorService.schedule(new Task(), 0, TimeUnit.MILLISECONDS);
    }

    public void testScheduleAtFixDelay() {
        scheduledExecutorService.scheduleWithFixedDelay(new Task(), 0, 1000, TimeUnit.MILLISECONDS);
    }

    public void testScheduleAtFixRate() {
        scheduledExecutorService.scheduleAtFixedRate(new Task(), 0, 1000, TimeUnit.MILLISECONDS);
    }


    public class Task implements Runnable {
        private final AtomicInteger retryTimes = new AtomicInteger(0);

        @Override
        public void run() {
            try {
                retryTimes.getAndIncrement();
                log.info("任务执行...........{}", retryTimes.get());
                if (retryTimes.get() > 5) {
                    // throw new IllegalArgumentException("测试中断");
                    scheduledExecutorService.shutdown();
                }
            } catch (Exception ex) {
                log.error("任务执行异常：{}", ex.getMessage(), ex);
            }
        }
    }

}
