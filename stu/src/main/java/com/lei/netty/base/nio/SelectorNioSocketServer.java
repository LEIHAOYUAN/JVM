package com.lei.netty.base.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @Author leihaoyuan
 * @Date 2022/1/4 10:05
 * @Version 1.0
 * @Description 多路复用器实现非阻塞socket-server
 */
public class SelectorNioSocketServer {


    public static void main(String[] args) throws Exception {
        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.bind(new InetSocketAddress(8998));
        System.out.println("listener on port:" + 8998);
        Selector selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_ACCEPT);
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
        byteBuffer.order(ByteOrder.BIG_ENDIAN);

        while (true) {
            int n = selector.select();
            if (n == 0) {
                continue;
            }
            // 当前channel就绪的事件
            Iterator<SelectionKey> ite = selector.selectedKeys().iterator();
            while (ite.hasNext()) {
                SelectionKey key = ite.next();
                // 被接收
                if (key.isAcceptable()) {
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    SocketChannel channel = server.accept();
                    registerChannel(selector, channel, SelectionKey.OP_READ);
                    replyClient(channel, byteBuffer);
                }
                // 可读
                if (key.isReadable()) {
                    readDataFromSocket(key, byteBuffer);
                }
                // 处理完移除
                ite.remove();
            }

        }

    }

    private static void registerChannel(Selector selector, SocketChannel channel, int ops) throws Exception {
        if (channel == null) {
            return;
        }
        channel.configureBlocking(false);
        channel.register(selector, ops);
    }

    private static void replyClient(SocketChannel channel, ByteBuffer byteBuffer) throws IOException {
        byteBuffer.clear();
        byteBuffer.put("hello client!\r\n".getBytes());
        byteBuffer.flip();
        channel.write(byteBuffer);
    }

    private static void readDataFromSocket(SelectionKey key, ByteBuffer byteBuffer) throws Exception {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        int count;
        byteBuffer.clear();
        while ((count = socketChannel.read(byteBuffer)) > 0) {
            byteBuffer.flip(); // Make buffer readable
            // Send the data; don't assume it goes all at once
            while (byteBuffer.hasRemaining()) {
                socketChannel.write(byteBuffer);
            }
            byteBuffer.clear(); // Empty buffer
        }
        if (count < 0) {
            socketChannel.close();
        }
    }


}
