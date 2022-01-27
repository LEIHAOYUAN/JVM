package com.lei.jvm.classload.initialization;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author leihaoyuan
 * @Date 2022/1/27 9:40
 * @Version 1.0
 * @Description 被动使用不会导致类的初始化
 */
@Slf4j
public class NotInitialization {

    public static void main(String[] args) {
        // 子类使用父类静态字段，不会导致子类的初始化
        useStaticField();
        log.info("--------------------------------------------------------");
        // 通过数组定义来引用类，不会触发此类的初始化
        useArray();
        log.info("--------------------------------------------------------");
        // 引用常量不会导致类的初始化
        useConst();
    }

    /**
     * 对于静态字段，只有直接定义这个字段的类才会被初始化，因此通过其子类来引用父类中定义的静态字段，只会触发父类的初始化而不会触发子类的初始化。
     */
    private static void useStaticField() {
        // 通过子类引用父类的静态字段，不会导致子类初始化
        System.out.println(SubClass.value);
    }

    /**
     * 通过数组定义来引用类，不会触发此类的初始化
     */
    private static void useArray() {
        SuperClass[] sca = new SuperClass[10];
    }

    /**
     * 引用常量，不会导致类的初始化
     * 【原因】：
     * 这是因为虽然在Java源码中确实引用了
     * ConstClass类的常量HELLOWORLD，但其实在编译阶段通过常量传播优化，已经将此常量的值“hello
     * world”直接存储在NotInitialization类的常量池中，以后NotInitialization对常量
     * ConstClass.HELLOWORLD的引用，实际都被转化为NotInitialization类对自身常量池的引用了。
     * 也就是说，实际上NotInitialization的Class文件之中并没有ConstClass类的符号引用入口，这两个类在编译成Class文件后就已不存在任何联系了。
     */
    private static void useConst() {
        System.out.println(ConstClass.HELLOWORLD);
    }


}
