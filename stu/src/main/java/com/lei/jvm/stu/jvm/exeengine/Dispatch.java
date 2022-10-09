package com.lei.jvm.stu.jvm.exeengine;

/**
 * @Author leihaoyuan
 * @Date 2022/1/27 18:27
 * @Version 1.0
 * @Description 单分派和多分派
 * 定义：方法的接收者与方法的参数统称为方法的宗量，这个定义最早应该来源于著名的《Java与模式》
 * 一书。根据分派基于多少种宗量，可以将分派划分为单分派和多分派两种。单分派是根据一个宗量对
 * 目标方法进行选择，多分派则是根据多于一个宗量对目标方法进行选择。
 */
public class Dispatch {

    static class QQ {
    }

    static class _360 {
    }

    public static class Father {
        public void hardChoice(QQ arg) {
            System.out.println("father choose qq");
        }

        public void hardChoice(_360 arg) {
            System.out.println("father choose 360");
        }
    }

    public static class Son extends Father {
        public void hardChoice(QQ arg) {
            System.out.println("son choose qq");
        }

        public void hardChoice(_360 arg) {
            System.out.println("son choose 360");
        }
    }

    public static void main(String[] args) {
        Father father = new Father();
        Father son = new Son();
        father.hardChoice(new _360());
        son.hardChoice(new QQ());
    }

}
