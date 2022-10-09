package com.lei.jvm.stu.design.chain;

import com.lei.jvm.stu.design.chain.handler.AbstractHandler;
import com.lei.jvm.stu.design.chain.handler.ConcreteHandler1;
import com.lei.jvm.stu.design.chain.handler.ConcreteHandler2;

/**
 * @Author leihaoyuan
 * @Date 2021/12/9 18:53
 * @Version 1.0
 * @Description
 */
public class ChainOfResponsibilityPatternClient {


    public static void main(String[] args) {
        // 可以利用管道模式自动处理责任链的组装
        AbstractHandler handler1 = new ConcreteHandler1();
        AbstractHandler handler2 = new ConcreteHandler2();
        handler1.setNext(handler2);
        handler1.invoke("接收请求");
    }

}
