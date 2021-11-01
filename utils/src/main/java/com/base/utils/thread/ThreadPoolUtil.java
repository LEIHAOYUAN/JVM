package com.base.utils.thread;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 该线程池工具类
 *
 * @author: 颜伟晗
 * @time: 2021/05/24
 */
@Slf4j
public class ThreadPoolUtil {
    private static final ThreadPoolExecutor POOL = create(8, 2000);

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
}
