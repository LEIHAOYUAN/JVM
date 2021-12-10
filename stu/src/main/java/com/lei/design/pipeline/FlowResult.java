package com.lei.design.pipeline;

/**
 * @Author leihaoyuan
 * @Date 2021/12/10 9:59
 * @Version 1.0
 * @Description
 */
public class FlowResult {

    public static FlowResult ok() {
        return new FlowResult();
    }

    public static FlowResult fail(String msg) {
        return new FlowResult();
    }

}
