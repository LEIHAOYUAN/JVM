package com.lei.jvm.stu.jvm.oom;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @Author leihaoyuan
 * @Date 2022/1/21 14:51
 * @Version 1.0
 * @Description 本机直接内存溢出
 * <p>
 * 直接内存（Direct Memory）的容量大小可通过-XX：MaxDirectMemorySize参数来指定，如果不去指定，则默认与Java堆最大值（由-Xmx指定）一致。
 * 示例代码没有使用DirectByteBuffer类，而是直接通过反射获取Unsafe实例进行内存分配（Unsafe类的getUnsafe()方法指定只有引导类加载器才会返回实例，体现了设计者希望只有虚拟机标准类库里面的类才能使用Unsafe的功能。在JDK 10时才将Unsafe的部分功能通过VarHandle开放给外部使用）。
 * 因为虽然使用DirectByteBuffer分配内存也会抛出内存溢
 * 出异常，但它抛出异常时并没有真正向操作系统申请分配内存，而是通过计算得知内存无法分配就会
 * 在代码里手动抛出溢出异常，真正申请分配内存的方法是Unsafe::allocateMemory()。
 * <p>
 * VM Args：-Xmx20M -XX:MaxDirectMemorySize=10M
 */
public class DirectMemoryOOM {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws Exception {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }


}
