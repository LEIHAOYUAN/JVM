package com.lei.stu.io.base;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * @Author leihaoyuan
 * @Date 2021/12/22 18:08
 * @Version 1.0
 * @Description Java NIO管道是两个线程之间的单向数据连接。Pipe有一个source通道和一个sink通道，数据会被写入到sink通道，从source通道读取
 */
public class PipeTest {

    public static void main(String[] args) throws IOException {
        // 获取管道
        Pipe pipe = Pipe.open();

        // 将缓冲区的数据写入管道
        ByteBuffer buf = ByteBuffer.allocate(1024);

        // 发送数据（使用sink发送）
        Pipe.SinkChannel sinkChannel = pipe.sink();
        buf.put("通过单向管道发送数据".getBytes());

        buf.flip();
        sinkChannel.write(buf);

        // 读取缓冲区中的数据（使用source接收）
        Pipe.SourceChannel sourceChannel = pipe.source();
        buf.flip();
        int len = sourceChannel.read(buf);
        System.out.println(new String(buf.array(), 0, len));

        sourceChannel.close();
        sinkChannel.close();
    }


}
