package com.stu.serializable;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * 结论：
 * 类ObjectSerTest1 的运行结果显示count=2，似乎被序列化了，但是类Test1的运行结果显示count=0并未被序列化。
 * ”序列化保存的是对象的状态，静态变量数以类的状态，因此序列化并不保存静态变量。
 * 这里的不能序列化的意思，是序列化信息中不包含这个静态成员域
 * ObjectSerTest1 测试成功，是因为都在同一个机器（而且是同一个进程），因为这个jvm已经把count加载进来了，所以你获取的是加载好的count，如果你是传到另一台机器或者你关掉程序重写写个程序读入test.obj，此时因为别的机器或新的进程是重新加载count的，所以count信息就是初始时的信息。“-----来自参考网页
 * 重写类Test1读取test.obj显示的结果是count的初始时的信息，也验证了上面一段话。
 * 最后，Java对象的static，transient 修饰的属性不能被序列化。
 *
 * https://www.cnblogs.com/yexiubiao/p/5014015.html
 *
 */
public class Test1 {

    public static void main(String args[]) {
        try {
            FileInputStream fis = new FileInputStream("test.obj");
            ObjectInputStream ois = new ObjectInputStream(fis);

            Student1 s3 = (Student1) ois.readObject();
            Student1 s4 = (Student1) ois.readObject();

            System.out.println(s3);
            System.out.println(s4);

            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }
}