package com.lei.stu.thread;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2020/4/13 17:35
 */
public class DaemonTest {

    public static void main(String[] args) {
        notDaemonThread();
    }

    /**
     * 守护线程：
     * 主线程结束，JVM直接退出，守护线程不管是否运行结束都要伴随着JVM的退出而退出
     */
    public static void daemonThread() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread 结束...");
            }
        });
        //设置守护线程
        thread.setDaemon(true);
        thread.start();
        System.out.println("main 结束...");
    }

    /**
     * 非守护线程:
     * 不会伴随JVM退出而终止
     */
    public static void notDaemonThread() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread 结束...");
            }
        });
        thread.start();
        System.out.println("main 结束...");
    }
}
