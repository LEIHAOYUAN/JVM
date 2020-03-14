package com.test.thread.callback;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @Description https://yq.aliyun.com/articles/683063
 * @Author leihaoyuan
 * @Date 2019/12/14 17:55
 */
public class Callback_Test {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        test1();
//        test2();
    }

    private static void test1() throws ExecutionException, InterruptedException {
        Callable<Integer> callable = () -> {
            Thread.sleep(1000);
            Random random = new Random();
            return random.nextInt(100);
        };
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> future = executorService.submit(callable);
        System.out.println(System.currentTimeMillis() + " ready to do task");
        Integer result = future.get();
        System.out.println(System.currentTimeMillis() + " get task result! result=" + result);
        executorService.shutdown();
    }

    private static void test2() throws ExecutionException, InterruptedException {
        Callable<Integer> callable = () -> {
            Thread.sleep(1000);
            Random random = new Random();
            return random.nextInt(100);
        };

        FutureTask<Integer> task = new FutureTask<>(callable);
        Thread thread = new Thread(task);
        System.out.println(System.currentTimeMillis() + " ready to do task");
        thread.start();
        Integer result = task.get();
        System.out.println(System.currentTimeMillis() + " get task result! result=" + result);
    }


}
