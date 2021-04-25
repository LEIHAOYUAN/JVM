package com.stu.lock;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author leihaoyuan
 * @Date 2021/4/19 16:21
 * @Version 1.0
 * @Description
 * 如果add、compare方法没有加锁，并且由于a方法和b方法中的变量操作都不是原子性操作，
 * 所以会出现和预期结果不一致！！！
 * 解决方案：
 * 需要在add方法和compare方法上都加上锁
 */
@Slf4j
public class Interesting {

    public static void main(String[] args) {
        Interesting interesting = new Interesting();
        new Thread(() -> interesting.add()).start();
        new Thread(() -> interesting.compare()).start();
    }

    volatile int a = 1;
    volatile int b = 1;

    public synchronized void add() {
        log.info("add start");
        // 注意：a++、b++操作并不是原子操作
        for (int i = 0; i < 10000; i++) {
            a++;
            b++;
        }
        log.info("add done");
    }

    public synchronized void compare() {
        log.info("compare start");
        for (int i = 0; i < 10000; i++) {
            //注意：a < b 这种操作在字节码层面是加载a 、加载b、比较，并不是原子操作
            if (a < b) {
                log.info("a小于b=============a:{},b:{},{}", a, b, a > b);
            }
        }
        log.info("compare done");
    }


}
