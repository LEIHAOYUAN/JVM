package com.lei.jvm.stu.disruptor;


import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * disruptor
 * https://blog.csdn.net/qq_45455361/article/details/121119049
 */
public class DisruptorApp {
    public static void main(String[] args) {
//参数准备工作
        TestEventFactory testEventFactory = new TestEventFactory();
        int ringBufferSize = 1024 * 1024;
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        /**
         * 1. EventFactory：消息（event）工程对象
         * 2. ringBufferSize：容器的长度
         * 3. executor：线程池（建议使用自定义线程池）
         * 4. ProducerType：单生产者还是多生产者
         * 5. waitStrategy：等待策略
         */
        //1.实例化disruptor对象
        Disruptor<TestEvent> disruptor = new Disruptor<>(
                testEventFactory,
                ringBufferSize,
                executor,
                ProducerType.SINGLE,
                new BlockingWaitStrategy());

        //2.添加消费者的监听
        disruptor.handleEventsWith(new TestEventHandler());

        //3.启动disruptor
        disruptor.start();

        //4.获取实际存储数据的容器：RingBuffer
        RingBuffer<TestEvent> ringBuffer = disruptor.getRingBuffer();

        TestEventProducer producer = new TestEventProducer(ringBuffer);

        ByteBuffer bb = ByteBuffer.allocate(8);

        for (long i = 0; i < 100; i++) {
            bb.putLong(0, i);
            producer.sendData(bb);
        }

        disruptor.shutdown();
        executor.shutdown();

    }

}