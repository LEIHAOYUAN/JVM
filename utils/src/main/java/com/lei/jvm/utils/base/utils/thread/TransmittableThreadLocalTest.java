package com.lei.jvm.utils.base.utils.thread;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;
import com.alibaba.ttl.threadpool.TtlExecutors;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试阿里巴巴 TransmittableThreadLocal
 */
@Slf4j
public class TransmittableThreadLocalTest {

    public static void main(String[] args) throws InterruptedException {
        ttlRunnable();
        log.info("----------------------------------------");
        ttlExecutors();
    }

    /**
     * 使用ttlRunnable
     */
    private static void ttlRunnable() {
        TransmittableThreadLocal<String> inheritableThreadLocal = new TransmittableThreadLocal<>();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        inheritableThreadLocal.set("main thread-1");
        executorService.submit(TtlRunnable.get(() -> System.out.println("1 obtain inheritableThreadLocal in threadPool: " + inheritableThreadLocal.get())));

        inheritableThreadLocal.set("main thread-2");
        executorService.submit(TtlRunnable.get(() -> System.out.println("2 obtain inheritableThreadLocal in threadPool: " + inheritableThreadLocal.get())));

        inheritableThreadLocal.set("main thread-3");
        executorService.submit(TtlRunnable.get(() -> System.out.println("3 obtain inheritableThreadLocal in threadPool: " + inheritableThreadLocal.get())));

        inheritableThreadLocal.set("main thread-4");
        executorService.submit(TtlRunnable.get(() -> System.out.println("4 obtain inheritableThreadLocal in threadPool: " + inheritableThreadLocal.get())));
    }

    /**
     * 使用ttlExecutors测试
     */
    private static void ttlExecutors() {
        TransmittableThreadLocal<String> inheritableThreadLocal = new TransmittableThreadLocal<>();
        ExecutorService executorService = TtlExecutors.getTtlExecutorService(Executors.newSingleThreadExecutor());
        inheritableThreadLocal.set("main thread-1");
        executorService.submit(() -> System.out.println("1 obtain inheritableThreadLocal in threadPool: " + inheritableThreadLocal.get()));

        inheritableThreadLocal.set("main thread-2");
        executorService.submit(() -> System.out.println("2 obtain inheritableThreadLocal in threadPool: " + inheritableThreadLocal.get()));

        inheritableThreadLocal.set("main thread-3");
        executorService.submit(() -> System.out.println("3 obtain inheritableThreadLocal in threadPool: " + inheritableThreadLocal.get()));

        inheritableThreadLocal.set("main thread-4");
        executorService.submit(() -> System.out.println("4 obtain inheritableThreadLocal in threadPool: " + inheritableThreadLocal.get()));
    }


}
