package com.lei.stu.visitor;

public class ConcreteElementA extends Element {

    @Override
    public void accept(Visitor visitor) {
        visitor.visitConcreteElementA(this);
    }

    //其它方法
    public void operationA() {

    }

}