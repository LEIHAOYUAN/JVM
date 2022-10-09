package com.lei.jvm.stu.jvm.oom;

/**
 * @Author leihaoyuan
 * @Date 2022/1/21 14:54
 * @Version 1.0
 * @Description 虚拟机栈和本地方法栈溢出
 * VM Args：-Xss128k
 * 无论是由于栈帧太大还是虚拟机栈容量太小，当新的栈帧内存无法分配的时候，
 * HotSpot虚拟机抛出的都是StackOverflowError异常
 */
public class JavaVMStackSOF {

    private int stackLength = 1;
    public void stackLeak() {
        stackLength++;
        stackLeak();
    }
    public static void main(String[] args) throws Throwable {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length:" + oom.stackLength);
            throw e;
        }
    }

}
