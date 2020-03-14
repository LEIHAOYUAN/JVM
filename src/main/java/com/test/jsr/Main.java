package com.test.jsr;

public class Main {

    public static void main(String[] args) throws Exception {
        //PersonBuilder在编译之后才会生成，这里需要编译后才能这样写
      Person person  = new PersonBuilder().setAge(25).setName("doge").build();
    }
}