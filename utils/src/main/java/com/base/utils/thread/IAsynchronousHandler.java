package com.base.utils.thread;

import java.util.concurrent.Callable;

public interface IAsynchronousHandler extends Callable<Object> {
    void executeAfter(Throwable t);

    void executeBefore(Thread t);
}