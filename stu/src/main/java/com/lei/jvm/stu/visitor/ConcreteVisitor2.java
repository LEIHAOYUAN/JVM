package com.lei.jvm.stu.visitor;

public class ConcreteVisitor2 extends Visitor {

    @Override
    public void visitConcreteElementA(ConcreteElementA concreteElementA) {
        System.out.println(concreteElementA.getClass().getName() + " 被 " + this.getClass().getName() + " 访问");
    }

    @Override
    public void visitConcreteElementB(ConcreteElementB concreteElementB) {
        System.out.println(concreteElementB.getClass().getName() + " 被 " + this.getClass().getName() + " 访问");
    }

}