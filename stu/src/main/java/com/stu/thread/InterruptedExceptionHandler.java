package com.stu.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author leihaoyuan
 * @Date 2021/8/17 17:47
 * @Version 1.0
 * @Description https://www.iteye.com/blog/coolxing-1474375
 */
@Slf4j
public class InterruptedExceptionHandler implements Runnable {

    private Object lock = new Object();

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            dosomething();
        }
    }

    private void dosomething() {
        try {
            // Object.wait是一个可中断的阻塞方法,
            // 如果在其阻塞期间检查到当前线程的中断标记为true,
            // 会重置中断标记后从阻塞状态返回,
            // 并抛出InterruptedException异常
            synchronized (lock) {
                lock.wait();
            }
        } catch (InterruptedException e) {
            log.info("异常后中断标记值：{}",Thread.currentThread().isInterrupted());

            // catch住InterruptedException后设置当前线程的中断标记为true, 以供调用栈上层进行相应的处理
            // 在此例中, dosomething方法的调用栈上层是run方法.
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new InterruptedExceptionHandler());
        t.start();
        // 启动线程1s后设置其中断标记为true
        Thread.sleep(1000);
        t.interrupt();
    }


}
