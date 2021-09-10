package com.stu.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2019/11/23 10:39
 */
public class CyclicBarrier_Test {

    public static void main(String[] args) {
        CyclicBarrier cb = new CyclicBarrier(5);
        for (int i = 0; i < 5; i++) {
            new Writer1(cb).start();
        }
    }
}

class Writer1 extends Thread {
    private CyclicBarrier cyclicBarrier;

    public Writer1(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
            System.out.println("线程：" + Thread.currentThread().getName() + "执行完毕，等待放行。。。。");
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("已放行------------------------------------");
    }
}
