package com.lei.jvm.utils.base.utils.thread;

import java.util.concurrent.Future;

public class ThreadPoolAdaptor implements IAsynchronousHandler {
    private IAsynchronousHandler handler;
    private Future<Object> future;
    private final long executeTime;

    public ThreadPoolAdaptor(IAsynchronousHandler handler, long time) {
        this.handler = handler;
        this.executeTime = System.currentTimeMillis() + time;
    }

    public IAsynchronousHandler getHandler() {
        return this.handler;
    }

    Future<Object> getFuture() {
        return this.future;
    }

    void setFuture(Future<Object> future) {
        this.future = future;
    }

    long getExecuteTime() {
        return this.executeTime;
    }

    public Object call() throws Exception {
        return this.handler.call();
    }

    public void executeAfter(Throwable t) {
        this.handler.executeAfter(t);
    }

    public void executeBefore(Thread t) {
        this.handler.executeBefore(t);
    }
}
