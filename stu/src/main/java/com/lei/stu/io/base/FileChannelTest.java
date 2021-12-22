package com.lei.stu.io.base;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @Author leihaoyuan
 * @Date 2021/12/22 16:10
 * @Version 1.0
 * @Description 通道 Channel，用于源节点与目标节点的连接，在Java NIO中 负责缓冲区中的数据传输。Channel本身不存储数据，因此需要配合缓冲区进行传输。
 */
public class FileChannelTest {

    public static void main(String[] args) {

    }

    /**
     * 利用通道完成文件的复制
     */
    private static void copyFileWithChannel() throws IOException {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        fis = new FileInputStream("1.jpg");
        fos = new FileOutputStream("2.jpg");

        // 获取通道
        inChannel = fis.getChannel();
        outChannel = fos.getChannel();

        //分配一个指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        // 将通道中的数据，存入缓冲区
        while (inChannel.read(buf) != -1) {
            // 切换成读取数据的模式
            buf.flip();
            // 将缓冲区中的数据写入通道
            outChannel.write(buf);
            // 清空缓冲区
            buf.clear();
        }
        // 关闭流
        fis.close();
        fos.close();
        // 关闭通道
        outChannel.close();
        inChannel.close();

    }

    /**
     * 利用通道完成文件的复制（直接缓存区，内存映射）
     *
     * @throws IOException
     */
    private static void copyFileWithMap() throws IOException {
        // 获取通道
        FileChannel inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("2.jpg"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE_NEW);

        // 得到的一个内存映射文件
        // 这个的好处是，直接将文件存储在内存中了
        MappedByteBuffer inMappedBuf = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMappedBuf = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

        // 直接对缓冲区进行数据的读写操作
        byte[] dst = new byte[inMappedBuf.limit()];
        inMappedBuf.get(dst);
        outMappedBuf.put(dst);

        inChannel.close();
        outChannel.close();
    }

    /**
     * 利用通道直接进行数据传输
     */
    private static void testTransferWithChannel() throws IOException {
        // 获取通道
        FileChannel inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("2.jpg"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE_NEW);

        // 从 inChannel通道 到 outChannel通道
        inChannel.transferTo(0, inChannel.size(), outChannel);

        inChannel.close();
        outChannel.close();
    }

}
