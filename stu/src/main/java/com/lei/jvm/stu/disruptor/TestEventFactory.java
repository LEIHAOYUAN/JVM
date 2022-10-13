package com.lei.jvm.stu.disruptor;

import com.lmax.disruptor.EventFactory;

public class TestEventFactory implements EventFactory<TestEvent> {
    @Override
    public TestEvent newInstance() {

        return new TestEvent();//这个方法就是为了返回空的数据对象
    }
}
