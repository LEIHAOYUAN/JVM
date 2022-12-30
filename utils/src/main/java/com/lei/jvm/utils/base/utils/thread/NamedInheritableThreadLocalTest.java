package com.lei.jvm.utils.base.utils.thread;

import cn.hutool.core.thread.threadlocal.NamedInheritableThreadLocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *  职能描述：
 *  @author leihaoyuan
 *  @version 2022/12/30 10:28
 */
public class NamedInheritableThreadLocalTest {

    public static void main(String[] args) throws InterruptedException {

        NamedInheritableThreadLocal<String> inheritableThreadLocal = new NamedInheritableThreadLocal<>("test");

        ExecutorService executorService = Executors.newSingleThreadExecutor();
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
