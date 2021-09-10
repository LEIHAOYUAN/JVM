package com.base.utils.thread;

public abstract class AbstractAsynchronousHandler implements IAsynchronousHandler {
    @Override
    public void executeAfter(Throwable throwable) {
    }

    @Override
    public void executeBefore(Thread thread) {
    }
}