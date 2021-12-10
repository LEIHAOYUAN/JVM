package com.lei.design.pipeline;

public interface Valve {
    /**
     * 获取下一个阀门
     * @return Valve 阀门
     */
    Valve getNext();

    /**
     * 设置下一个阀门
     * @param valve 阀门
     */
    void setNext(Valve valve);

    /**
     * 执行管道
     * @param pipeLineContext 管道上下文
     * @return FlowResult
     */
    FlowResult invoke(PipeLineContext pipeLineContext);
}