package com.lei.jvm.stus.stu.disruptor;

import com.lmax.disruptor.EventHandler;

public class TestEventHandler implements EventHandler<TestEvent> {
    @Override
    public void onEvent(TestEvent testEvent, long l, boolean b) throws Exception {
        System.out.println("消费者："+testEvent.getValue());
    }
}
