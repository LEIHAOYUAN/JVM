package com.lei.jvm.stu.jvm.oom;

/**
 * @Author leihaoyuan
 * @Date 2022/1/21 14:58
 * @Version 1.0
 * @Description 创建线程导致内存溢出异常
 * VM Args：-Xss2M （这时候不妨设大些，请在32位系统下运行）
 * 如果是建立过多线程导致的内存溢出，在不能减少线程数量或者更换64位虚拟机的情况下，就只能通过减少最大堆和减少栈容量来换取更多的线程。
 */
public class JavaVMStackOOM {

    private void dontStop() {
        while (true) {
        }
    }

    public void stackLeakByThread() {
        while (true) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
            thread.start();
        }
    }

    public static void main(String[] args) throws Throwable {
        JavaVMStackOOM oom = new JavaVMStackOOM();
        oom.stackLeakByThread();
    }


}
