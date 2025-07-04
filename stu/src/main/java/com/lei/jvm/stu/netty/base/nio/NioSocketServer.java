package com.lei.jvm.stu.netty.base.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;

/**
 * 单线程非阻塞的socket server，不用多路复用器，可以同时支持多个客户端
 * 缺点：每次循环将遍历所有的客户端连接，效率低下
 */
@Slf4j
public class NioSocketServer {
    //用于存放连接上来的SocketChannel
    private static LinkedList<SocketChannel> clients = new LinkedList<>();

    public static void main(String[] args) {
        try {
            ServerSocketChannel server = ServerSocketChannel.open();
            server.socket().bind(new InetSocketAddress(9090), 100);
            //设置ServerSocketChannel为非阻塞
            server.configureBlocking(false);
            //分配4KB堆外内存作为读缓冲
            ByteBuffer readBuffer = ByteBuffer.allocateDirect(4 * 1024);
            log.info("single thread non-blocking socket server start...");
            while (true) {
                //处理新练上来的连接。非阻塞，立即返回null或者具体的SocketChannel
                SocketChannel newClient = server.accept();
                if (null != newClient) {
                    newClient.configureBlocking(false);
                    log.info("accept a client socket, port=" + newClient.socket().getPort());
                    clients.add(newClient);
                }
                //处理正连着的连接
                for (SocketChannel client : clients) {
                    //从内核的socket缓冲读count个字节数据到用户态的read buffer
                    int count = client.read(readBuffer);
                    if (count > 0) {
                        //readBuffer内部的limit指针指向当前最后写入的位置，position指向0起始位
                        readBuffer.flip();
                        byte[] bytes = new byte[readBuffer.limit()];
                        //把read buffer中当前可用数据存到bytes数组
                        readBuffer.get(bytes);
                        String msg = new String(bytes);
                        log.info("receive from client[{}:{}], msg:{}", client.socket().getInetAddress(), client.socket().getPort(), msg);
                        readBuffer.clear();
                        ByteBuffer writeBuffer = ByteBuffer.wrap(("echo from server:" + msg).getBytes());
                        client.write(writeBuffer);
                    }
                }
                //防止CPU空转
                Thread.sleep(500);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}