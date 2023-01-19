package com.lei.jvm.stu.reference;

import lombok.extern.slf4j.Slf4j;

/**
 *  职能描述：
 *  @author leihaoyuan
 *  @version 2023/1/19 14:42
 */
@Slf4j
public class ReferenceTest {

    public static void main(String[] args) {
        B b = new B();
        A a = new A(b);
        b.setA(a);
    }


}

class A {
    private B b;

    public A(B b) {
        this.b = b;
    }
}

class B {
    private A a;

    public void setA(A a){
        this.a = a;
    }
}
