package com.test.thread;

/**
 * @Description 测试不同状态下线程被中断后的行为
 * @Author lhy
 * @Date 2019/11/21 15:01
 */
public class TheadStatusInterruptDemo {
    public static void main(String[] args) throws InterruptedException {
//        testRunningInterrupt();
//        testBlockedInterrupt();
        testRunningAfterBlockedInterrupt();
    }

    /**
     * 测试阻塞状态下被interrupt
     * @throws InterruptedException
     */
    private static void testBlockedInterrupt() throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        Thread.currentThread().sleep(5000);
                    } catch (InterruptedException e) {
                        System.out.println("catch:" + Thread.currentThread().isInterrupted());
                        break;
                    }
                }
                //经过catch InterruptedException 处理后的线程中断状态已经被重置
                System.out.println("interrupted!!!:" + Thread.currentThread().isInterrupted());
            }
        });
        t1.start();
        Thread.currentThread().sleep(2000);
        t1.interrupt();
        t1.join();
    }

    /**
     * 测试运行中状态的线程被interrupt
     * @throws InterruptedException
     */
    private static void testRunningInterrupt() throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println("running...");
                }
                //中断处理的代码
                System.out.println("interrupted!!!" + Thread.currentThread().isInterrupted());
            }
        });
        t1.start();
        Thread.currentThread().sleep(2000);
        t1.interrupt();
        t1.join();
    }

    /**
     * 测试运行中状态的线程被interrupt
     * @throws InterruptedException
     */
    private static void testRunningAfterBlockedInterrupt() throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (true) {
                    System.out.println("running...:"+i++);
                    if(i>5000){
                        try {
                            System.out.println("sleep：" + Thread.currentThread().isInterrupted());
                            //阻塞之前，interrupt被设置为true，此次会抛出异常
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            System.out.println("interrupted!!!" + Thread.currentThread().isInterrupted());
                            break;
                        }
                    }
                }
            }
        });
        t1.start();
        t1.interrupt();
        t1.join();
    }
}
