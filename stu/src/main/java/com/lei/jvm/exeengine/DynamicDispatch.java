package com.lei.jvm.exeengine;

/**
 * @Author leihaoyuan
 * @Date 2022/1/27 18:02
 * @Version 1.0
 * @Description 动态分派
 * 通过javap命令看到：invokevirtual #22; //Method org/fenixsoft/polymorphic/Dynamic Dispatch$Human.sayHello:()V
 * 根据《Java虚拟机规范》：
 * invokevirtual指令的运行时解析过程 [4] 大致分为以下几步：
 * 1）找到操作数栈顶的第一个元素所指向的对象的实际类型，记作C。
 * 2）如果在类型C中找到与常量中的描述符和简单名称都相符的方法，则进行访问权限校验，如果通过则返回这个方法的直接引用，查找过程结束；不通过则返回java.lang.IllegalAccessError异常。
 * 3）否则，按照继承关系从下往上依次对C的各个父类进行第二步的搜索和验证过程。
 * 4）如果始终没有找到合适的方法，则抛出java.lang.AbstractMethodError异常
 *
 * 原理：java的多态性根源在于虚方法调用指令invokevirtual的执行逻辑，从而得出结论：只对方法有效，对字段无效，因为字段不使用该指令
 *
 */
public class DynamicDispatch {

    static abstract class Human {
        protected abstract void sayHello();
    }

    static class Man extends Human {
        @Override
        protected void sayHello() {
            System.out.println("man say hello");
        }
    }

    static class Woman extends Human {
        @Override
        protected void sayHello() {
            System.out.println("woman say hello");
        }
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        man.sayHello();
        woman.sayHello();
        man = new Woman();
        man.sayHello();
    }

}
