package com.lei.jvm.stu.jvm.volatilet;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 *  职能描述：volatile修饰对象效果测试
 *  @author leihaoyuan
 *  @version 2022/9/22 17:16
 *  https://blog.51cto.com/u_15057819/2623150
 */
@Slf4j
public class VolatileEntityTest {

    //使用volatile修饰共享资源
    private static volatile VolatileEntity volatileEntity = VolatileEntity.getInstance();

    private static final CountDownLatch latch = new CountDownLatch(10);

    public static void main(String args[]) throws InterruptedException {
        //启动一个线程，当发现local_value与init_value不同时，则输出init_value被修改的值
        new Thread(() -> {
            int localValue = volatileEntity.init_value;
            while (localValue < VolatileEntity.max) {
                if (volatileEntity.init_value != localValue) {
                    log.info("The init_value is update ot {}", volatileEntity.init_value);
                    //对localValue进行重新赋值
                    localValue = volatileEntity.init_value;
                }
            }
        }, "Reader").start();

        //启动updater线程，主要用于对init_value的修改，当local_value=5的时候退出生命周期
        new Thread(() -> {
            int localValue = volatileEntity.init_value;
            while (localValue < VolatileEntity.max) {
                //修改init_value
                log.info("The init_value will be changed to {}", ++localValue);
                volatileEntity.init_value = localValue;
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Updater").start();
    }
}

class VolatileEntity {
    //类变量
    final static int max = 5;
    int init_value = 0;

    public static int getMax() {
        return max;
    }

    public int getInit_value() {
        return init_value;
    }

    public void setInit_value(int init_value) {
        this.init_value = init_value;
    }

    private static class VolatileEntityHolder {
        private static VolatileEntity instance = new VolatileEntity();
    }

    public static VolatileEntity getInstance() {
        return VolatileEntityHolder.instance;
    }
}


