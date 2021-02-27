package com.stu.classload;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2019/10/21 16:55
 */
public class MyTest8 {

    public static void main(String[] args) {
        Fruit apple = new Apple();
        Fruit orange = new Orange();
        apple.test();
        orange.test();

    }
}

class Fruit{
    public void test(){
        System.out.println("Fruit");
    }
}

class Apple extends Fruit{
    @Override
    public void test() {
        System.out.println("Apple");
          }
}

class Orange extends Fruit{

}
