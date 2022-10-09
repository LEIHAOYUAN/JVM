package com.lei.jvm.stu.io.base;

import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;

/**
 * @Author leihaoyuan
 * @Date 2021/12/22 15:58
 * @Version 1.0
 * @Description https://blog.csdn.net/weixin_43314519/article/details/109993897
 */
@Slf4j
public class ByteBufferTest {

    public static void main(String[] args) {

    }

    /**
     * 测试buffer基本属性
     */
    private static void testBufferProperties() {
        // 分配一个指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);
        log.info("初始化");
        log.info("position：" + buf.position());
        log.info("limit：" + buf.limit());
        log.info("capacity：" + buf.capacity());

        // 存入数据到缓冲区
        String str = "abcde";
        buf.put(str.getBytes());
        log.info("存入数据");
        log.info("position：" + buf.position());
        log.info("limit：" + buf.limit());
        log.info("capacity：" + buf.capacity());

        // 切换读取数据模式
        buf.flip();
        log.info("切换读取数据模式");
        log.info("position：" + buf.position());
        log.info("limit：" + buf.limit());
        log.info("capacity：" + buf.capacity());

        // 开始读取数据
        log.info("开始读取数据");
        byte[] dst = new byte[buf.limit()];
        buf.get(dst);
        log.info(new String(dst, 0, dst.length));

        log.info("数据读取完毕");
        log.info("position：" + buf.position());
        log.info("limit：" + buf.limit());
        log.info("capacity：" + buf.capacity());

        // rewind()：表示重复读
        buf.rewind();
        log.info("rewind");
        log.info("position：" + buf.position());
        log.info("limit：" + buf.limit());
        log.info("capacity：" + buf.capacity());

        // clear()：清空缓冲区，但是缓冲区中的数据仍然存储，但是处于被遗忘状态
        buf.clear();
        log.info("clear");
        log.info("position：" + buf.position());
        log.info("limit：" + buf.limit());
        log.info("capacity：" + buf.capacity());
    }

}
