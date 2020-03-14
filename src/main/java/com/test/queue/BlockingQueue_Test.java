package com.test.queue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @Description 通过阻塞队列实现生产者消费者模式
 * @Author leihaoyuan
 * @Date 2019/11/22 11:22
 */
public class BlockingQueue_Test {
    private static final int MAX_CAACITY = 10;
    private static ArrayBlockingQueue<Object> goods = new ArrayBlockingQueue<>(MAX_CAACITY);

    public static void main(String[] args) {
        new Producer().start();
        new Consumer().start();
    }


    static class Producer extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1500);
                    goods.put(new Object());
                    System.out.println("生产者：+1,当前产品：" + goods.size());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    static class Consumer extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1200);
                    goods.take();
                    System.out.println("消费者：-1，当前剩余：" + goods.size());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
