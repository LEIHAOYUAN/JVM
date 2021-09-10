package com.base.utils.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author leihaoyuan
 * @Date 2021/3/20 14:02
 * @Version 1.0
 * @Description yield 使当前线程让出 CPU 时间片，线程从运行状态（Running）变为可执行状态（Runnable），处于可执行状态的线程有可能会再次获取到时间片继续执行，也有可能处于等待状态，直到再次获取到时间片。也就是说，后续会有两种情况：
 * 1、当前线程让出 CPU 时间片后，又立即获取到 CPU 时间片，进而继续执行当前方法。
 * 2、当前线程让出 CPU 时间片后，等待一段时间后获取到 CPU 时间片，进而继续执行当前方法。
 */
public class YieldTest {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new Worker(), "thread1");
        Thread thread2 = new Thread(new Worker(), "thread2");

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(thread1);
        executorService.execute(thread2);
        executorService.shutdown();
        System.out.println("Main结束......");
    }
}

class Worker implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i <= 10; i++) {
            System.out.println("threadName=" + Thread.currentThread().getName() + ", index=" + i);
            if (5 == i) {
                Thread.yield();
                System.out.println("threadName=" + Thread.currentThread().getName() + ", 执行 yield 方法，让出 CPU 时间片");
            }
        }
    }
}

