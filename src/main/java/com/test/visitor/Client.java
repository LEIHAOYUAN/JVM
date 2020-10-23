package com.test.visitor;

/**
 * 访问者模式
 * 它讲的是表示一个作用于某对象结构中的各元素的操作，
 * 它使你可以在不改变各元素的类的前提下定义作用于这些元素的新操作。
 * 例如，你在朋友家做客，你是访问者，朋友接收你的访问，你通过朋友的描述，
 * 然后对朋友的描述做出一个判断，这就是访问者模式。
 *
 */
public class Client {

    public static void main(String[] args) {

        ObjectStructure objectStructure = new ObjectStructure();

        objectStructure.attach(new ConcreteElementA());
        objectStructure.attach(new ConcreteElementB());

        ConcreteVisitor1 visitor1 = new ConcreteVisitor1();
        ConcreteVisitor2 visitor2 = new ConcreteVisitor2();

        objectStructure.accept(visitor1);
        objectStructure.accept(visitor2);
    }

}