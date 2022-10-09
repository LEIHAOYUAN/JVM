package com.lei.jvm.stu.jvm.oom;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author leihaoyuan
 * @Date 2022/1/21 14:23
 * @Version 1.0
 * @Description 方法区和运行时常量池溢出
 */
public class RuntimeConstantPoolOOM {

    public static void main(String[] args) {
        internReference();
    }

    /**
     * 自JDK 7起，原本存放在永久代的字符串常量池被移至Java堆之中
     */
    private static void heapSpaceOutOfMemoryError() {
        // 使用Set保持着常量池引用，避免Full GC回收常量池行为
        Set<String> set = new HashSet<String>();
        // 在short范围内足以让6MB的PermSize产生OOM了
        short i = 0;
        while (true) {
            set.add(String.valueOf(i++).intern());
        }
    }

    /**
     * 现象：JDK 6中运行，会得到两个false，而在JDK 7中运行，会得到一个true和一个false
     * 【JDK6】在JDK 6中，intern()方法会把首次遇到的字符串实例复制到永久代的字符串常量池
     * 中存储，返回的也是永久代里面这个字符串实例的引用，而由StringBuilder创建的字符串对象实例在
     * Java堆上，所以必然不可能是同一个引用，结果将返回false。
     *
     * 【JDK7】
     * JDK 7（以及部分其他虚拟机，例如JRockit）的intern()方法实现就不需要再拷贝字符串的实例
     * 到永久代了，既然字符串常量池已经移到Java堆中，那只需要在常量池里记录一下首次出现的实例引
     * 用即可，因此intern()返回的引用和由StringBuilder创建的那个字符串实例就是同一个。
     */
    private static void internReference() {
        // intern返回的引用和由StringBuilder创建的字符串是同一个
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);
        // “java”字符串常量池中已经有它的引用，不符合intern()方法要求“首次遇到”的原则，“计算机软件”这个字符串则是首次出现的，因此结果返回true。
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);
    }


}
