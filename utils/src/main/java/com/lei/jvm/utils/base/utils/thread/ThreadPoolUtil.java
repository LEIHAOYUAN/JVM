package com.lei.jvm.utils.base.utils.thread;


import cn.hutool.core.thread.NamedThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.NamedInheritableThreadLocal;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 该线程池工具类
 *
 * @author:
 * @time: 2021/05/24
 */
@Slf4j
public class ThreadPoolUtil {

    /**
     * 普通线程池
     */
    private static final ThreadPoolExecutor POOL = create(1, 2000);

    /**
     * 定时任务线程池
     */
    private static final ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(2,
            new NamedThreadFactory("scheduledExecutor",
                    false));

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

    public static void main(String[] args) throws InterruptedException {

        // 测试定时任务
        // scheduledExecutor.schedule(() -> log.info("延迟任务执行完毕......"),10,TimeUnit.SECONDS);
        // scheduledExecutor.shutdown();
        // 测试线程池
        // execute(() -> log.info("异步任务执行完毕........."));
        // 周期性的执行定时任务
        // scheduledExecutor.scheduleAtFixedRate(() -> log.info("周期任务执行........."), 2, 2, TimeUnit.SECONDS);

        ThreadLocal<String> THREAD_LOCAL = new ThreadLocal();
        THREAD_LOCAL.set("simple-thread-local");
        ThreadLocal<String> INHERITABLE_THREAD_LOCAL = new NamedInheritableThreadLocal("token");
        INHERITABLE_THREAD_LOCAL.set("inheritable");
        execute(new Runnable() {
            @Override
            public void run() {
                log.info("普通上下文={}", THREAD_LOCAL.get());
                log.info("可传递上下文={}", INHERITABLE_THREAD_LOCAL.get());
//                INHERITABLE_THREAD_LOCAL.set("AA");
                log.info("改变后可传递上下文={}", INHERITABLE_THREAD_LOCAL.get());
            }
        });

        Thread.sleep(100);
        log.info("传递线程变量={}", INHERITABLE_THREAD_LOCAL.get());
        execute(new Runnable() {
            @Override
            public void run() {
                log.info("再次获取可传递上下文={}", INHERITABLE_THREAD_LOCAL.get());
            }
        });
        log.info("main线程执行完毕.........END");
    }


    private static void testTimer() {
        // 定时器测试
        new Timer(false).schedule(new TimerTask() {
            @Override
            public void run() {
                log.info("定时器测试");
            }
        }, 6000);
    }

    private static void testScheduledWithDemon() {
        // 定时任务执行器
        scheduledExecutor.schedule(() -> log.info("延迟任务执行完毕......"), 2, TimeUnit.SECONDS);
    }
}
