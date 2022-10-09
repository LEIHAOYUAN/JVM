package com.lei.jvm.stu.design.chain.handler;

/**
 * @Author leihaoyuan
 * @Date 2021/12/9 18:33
 * @Version 1.0
 * @Description 抽象处理者
 */
public abstract class AbstractHandler {

    AbstractHandler next = null;

    public void setNext(AbstractHandler next) {
        this.next = next;
    }

    public AbstractHandler getNext() {
        return next;
    }

    public abstract void invoke(String request);


}
