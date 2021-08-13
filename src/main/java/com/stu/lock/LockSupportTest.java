package com.stu.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * @Author leihaoyuan
 * @Date 2021/8/12 18:46
 * @Version 1.0
 * @Description LockSupport测试
 */
@Slf4j
public class LockSupportTest {

    public static void main(String[] args) {
        lockSupport();
    }


    private static void normalThread() {
        final Object obj = new Object();
        Thread A = new Thread(() -> {
            int sum = 0;
            for (int i = 0; i < 10; i++) {
                sum += i;
            }
            try {
                synchronized (obj) {
                    obj.wait();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(sum);
        });
        A.start();
        //睡眠一秒钟，保证线程A已经计算完成，阻塞在wait方法
//        Thread.sleep(1000);
        synchronized (obj) {
            obj.notify();
        }
    }

    /**
     * https://www.cnblogs.com/liang1101/p/12785496.html
     * LockSupport是用来创建锁和其他同步类的基本线程阻塞原语。LockSupport 提供park()和unpark()方法实现阻塞线程和解除线程阻塞.
     * LockSupport和每个使用它的线程都有一个许可(permit)关联。
     * permit相当于1，0的开关，默认是0，调用一次unpark就加1变成1，
     * 调用一次park会消费permit, 也就是将1变成0，同时park立即返回。
     * 再次调用park会变成block（因为permit为0了，会阻塞在这里，直到permit变为1）,这时调用unpark会把permit置为1。
     * 每个线程都有一个相关的permit, permit最多只有一个，重复调用unpark也不会积累。
     */
    private static void lockSupport() {
        Thread A = new Thread(() -> {
            int sum = 0;
            for (int i = 0; i < 10; i++) {
                sum += i;
            }
            // 调用park后，如果当前permit==1，则将permit置为0后，立即返回
            // 如果当前permit == 0，此时线程会被阻塞，直到permit重新变为1
            LockSupport.park();
            System.out.println(sum);
        });
        A.start();
        // 调用unpark就是将当前permit重置为1，调用多次并不会累加
        LockSupport.unpark(A);
        //睡眠一秒钟，保证线程A已经计算完成，阻塞在wait方法
//        Thread.sleep(1000);

    }

}
