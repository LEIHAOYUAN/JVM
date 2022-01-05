package com.base.utils.thread;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * 该线程池工具类
 *
 * @author:
 * @time: 2021/05/24
 */
@Slf4j
public class ThreadPoolUtil {
    private static final ThreadPoolExecutor POOL = create(8, 2000);

    /**
     * 定时任务线程池
     */
    private static final ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(2);

    /**
     * @param size
     * @return
     */
    public static ThreadPoolExecutor create(int size) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(size, size * 2,
                60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1000),
                new ThreadPoolExecutor.CallerRunsPolicy());
        return threadPoolExecutor;
    }

    public static ThreadPoolExecutor create(int size, int queueSize) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(size, size * 2,
                60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(queueSize),
                new ThreadPoolExecutor.CallerRunsPolicy());
        return threadPoolExecutor;
    }

    public static void execute(Runnable runnable) {
        POOL.execute(runnable);
    }

    public static void main(String[] args) {
        // 测试定时任务
        // scheduledExecutor.schedule(() -> log.info("延迟任务执行完毕......"),10,TimeUnit.SECONDS);
        // scheduledExecutor.shutdown();
        // 测试线程池
        execute(new Runnable() {
            @Override
            public void run() {
                log.info("异步任务执行完毕.........");
            }
        });
    }
}
