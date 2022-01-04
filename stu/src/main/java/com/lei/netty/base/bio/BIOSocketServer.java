package com.lei.netty.base.bio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * java-bio实现socket
 */
@Slf4j
public class BIOSocketServer {

    public static void main(String[] args) {
        ServerSocket server = null;
        try {
            server = new ServerSocket();
            server.bind(new InetSocketAddress("localhost", 9090), 100); //侦听本地9090端口，backlog设置为100
            log.info("单线程BIO服务启动...");
            while (true) {
                //阻塞，等待可以接受一个客户端连接
                Socket client = server.accept();
                InputStream is = client.getInputStream();   //读取的输入流
                byte[] readBuf = new byte[4 * 1024];
                //read没返回-1说明流没有读完。没有数据读则会一直阻塞。
                while (is.read(readBuf) > 0) {
                    String msg = new String(readBuf);
                    log.info("receive from client[{}:{}], msg:{}"
                            , client.getInetAddress().toString()
                            , client.getPort()
                            , msg);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != server)
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}