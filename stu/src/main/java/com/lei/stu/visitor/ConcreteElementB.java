package com.lei.stu.visitor;

public class ConcreteElementB extends Element {

    @Override
    public void accept(Visitor visitor) {
        visitor.visitConcreteElementB(this);
    }

    //其它方法
    public void operationA() {

    }

}