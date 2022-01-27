package com.lei.jvm.exeengine;

/**
 * @Author leihaoyuan
 * @Date 2022/1/27 18:21
 * @Version 1.0
 * @Description 字段分派演示
 */
public class FieldHasNoPolymorphic2 {

    static class Father {
        public int money = 1;

        public Father() {
            money = 2;
            showMeTheMoney();
        }

        public void showMeTheMoney() {
            System.out.println("I am Father, i have $" + money);
        }
    }

    static class Son extends Father {
        public int money = 3;

        public Son() {
            money = 4;
            showMeTheMoney();
        }

        public void showMeTheMoney() {
            System.out.println("I am Son, i have $" + money);
        }
    }

    /**
     * 输出两句都是“I am Son”，这是因为Son类在创建的时候，首先隐式调用了Father的构造函数，而
     * Father构造函数中对showMeTheMoney()的调用是一次虚方法调用，实际执行的版本是
     * Son::showMeTheMoney()方法，所以输出的是“I am Son”，这点经过前面的分析相信读者是没有疑问的
     * 了。而这时候虽然父类的money字段已经被初始化成2了，但Son::showMeTheMoney()方法中访问的却
     * 是子类的money字段，这时候结果自然还是0，因为它要到子类的构造函数执行时才会被初始化。
     * main()的最后一句通过静态类型访问到了父类中的money，输出了2
     */
    public static void main(String[] args) {
        Father gay = new Son();
        System.out.println("This gay has $" + gay.money);
    }
}
