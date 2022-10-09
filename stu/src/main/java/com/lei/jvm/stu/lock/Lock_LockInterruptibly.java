package com.lei.jvm.stu.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Lock_LockInterruptibly {

    private Lock lock = new ReentrantLock();
    private int tickets = 20;

    public static void main(String[] args) throws InterruptedException {
        LockDemo lockDemo = new Lock_LockInterruptibly().new LockDemo();

        Thread thread = new Thread(lockDemo);
        thread.start();
//        thread.join();
        System.out.println("main-----------------");
        thread.interrupt();
    }

    class LockDemo implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    System.out.println("000000000000000000000000000000000");
                    lock.lockInterruptibly();
                    System.out.println("111111111111111111111111111111111");
                } catch (Exception e) {
                    System.out.println("exception");
                    break;
                } finally {
                    lock.unlock();
                }

            }
        }
    }


}
