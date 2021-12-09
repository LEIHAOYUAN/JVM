package com.lei.design.chain.handler;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author leihaoyuan
 * @Date 2021/12/9 18:36
 * @Version 1.0
 * @Description 具体执行者1
 */
@Slf4j
public class ConcreteHandler1 extends AbstractHandler{

    @Override
    public void invoke(String request) {
        log.info("处理者:1正在处理.........");
        if(null != getNext()){
            getNext().invoke(request);
        }
    }
}
