package com.lei.jvm.stu.jvm.hashcode;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

/**
 * @Author leihaoyuan
 * @Date 2021/12/20 10:38
 * @Version 1.0
 * @Description 测试GC前后，对象hashcode是否发生变化
 * https://zhuanlan.zhihu.com/p/363093604
 */
@Slf4j
public class TestHashCode {

    public static void main(String[] args) {
//        testHashCodeAndMemory();
        testSystemIdentityHashCode();
    }

    /**
     * 验证GC前后，对象地址、hashCode变化
     */
    private static void testHashCodeAndMemory() {
        Object obj = new Object();
        long address = VM.current().addressOf(obj);
        long hashCode = obj.hashCode();
        log.info("before GC : 内存地址：{} ", address);
        log.info("before GC : HashCode:{}", hashCode);

        new Object();
        new Object();
        new Object();

        System.gc();

        long afterAddress = VM.current().addressOf(obj);
        long afterHashCode = obj.hashCode();
        log.info("after GC : 内存地址：{} ", afterAddress);
        log.info("after GC : HashCode:{}", afterHashCode);

        log.info("GC前后地址比较：{}", address == afterAddress);
        log.info("GC前后HashCode比较：{}", hashCode == afterHashCode);
    }


    private static void testSystemIdentityHashCode() {
        // 创建对象并打印JVM中对象的信息
        Object person = new Object();
        System.out.println(ClassLayout.parseInstance(person).toPrintable());
        // 调用hashCode方法，如果重写了hashCode方法则调用System#identityHashCode方法
        System.out.println(person.hashCode());
        // System.out.println(System.identityHashCode(person));
        // 再次打印对象JVM中的信息
        System.out.println(ClassLayout.parseInstance(person).toPrintable());
    }


}
