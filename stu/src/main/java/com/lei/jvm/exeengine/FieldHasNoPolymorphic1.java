package com.lei.jvm.exeengine;

/**
 * @Author leihaoyuan
 * @Date 2022/1/27 18:15
 * @Version 1.0
 * @Description 验证字段不存在多态性
 * 事实上，在Java里面只有虚方法存在，字段永远不可能是虚的，换句话说，字段永远不参与多态，哪个类的方法访问某个名字的字段时，
 * 该名字指的就是这个类能看到的那个字段。
 * 当子类声明了与父类同名的字段时，虽然在子类的内存中两个字段都会存在，但是子类的字段会遮蔽父类的同名字段。
 */
public class FieldHasNoPolymorphic1 {

    static class Father {
        public int money = 1;

        public Father() {
            money = 2;
            System.out.println("I am Father, i have $" + money);
        }
    }

    static class Son extends Father {
        public int money = 3;

        public Son() {
            money = 4;
            System.out.println("I am Son, i have $" + money);
        }
    }

    public static void main(String[] args) {
        Father gay = new Son();
        System.out.println("This gay has $" + gay.money);
    }
}
