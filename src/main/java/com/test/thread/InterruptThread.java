package com.test.thread;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2019/11/21 15:01
 */
public class InterruptThread extends Thread {

    @Override
    public void run() {
        super.run();
        for(int i = 0; i <= 2000000; i++) {
            try {
                if(i>20){
                    System.out.println("sleep-try-0:"+Thread.currentThread().isInterrupted());
                    Thread.sleep(1000);
                    System.out.println("sleep-try-1:"+Thread.currentThread().isInterrupted());
                }
            } catch (InterruptedException e) {
                System.out.println("catch----------------------------");
                System.out.println("sleep-catch:"+Thread.currentThread().isInterrupted());
                return;
            }
            boolean interrupted = Thread.currentThread().isInterrupted();
            System.out.println(i+":"+interrupted);
            //判断是否被中断
            if(interrupted){
                //处理中断逻辑
                break;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        InterruptThread t = new InterruptThread();
        t.start();
        Thread.sleep(2);
        t.interrupt();
    }
}
