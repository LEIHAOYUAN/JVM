package com.lei.stu.class0;

/**
 * https://www.cnblogs.com/LemonPi/p/9846867.html
 * 构造方法中调用子类重写的方法，因为此时子类还未初始化，容易造成难以预料的错误，不推荐这样使用！
 */
public class Father {

    public static void main(String []args) {
        Father father = new Son();
    }

    public Father() {
        System.out.println("this is father before f()");
        f();
        System.out.println("this is father after f()");
    }
    public void f() {
        System.out.println("this is Father, in f()");
    }


}

//Son.java
class Son extends Father {

    public int field = 1;
    @Override
    public void f() {
        System.out.println("this is Son, in f()");
    }
}