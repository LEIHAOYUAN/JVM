package com.lei.jvm.stu.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * @Author leihaoyuan
 * @Date 2022/1/12 15:19
 * @Version 1.0
 * @Description 简单HTTPServer
 * 验证netty boosgroup实际线程数量问题：
 * https://zhuanlan.zhihu.com/p/378238584
 * 【netty】boosgroup为什么只会有一个实际线程原理：
 * https://blog.csdn.net/u013076044/article/details/103933774
 */
@Slf4j
public class HttpServer {

    public static void main(String[] args) throws Exception {

        NioEventLoopGroup bossGroup = new NioEventLoopGroup(4);
        NioEventLoopGroup workGroup = new NioEventLoopGroup(4);

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workGroup);
        serverBootstrap.channel(NioServerSocketChannel.class);
        serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) {
                ch.pipeline().addLast(new HttpServerCodec());
                ch.pipeline().addLast(new HttpObjectAggregator(1024 * 1024));
                ch.pipeline().addLast(new SimpleChannelInboundHandler<FullHttpRequest>() {
                    @Override
                    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpRequest httpRequest) {
                        byte[] content = "<h1>hello world</h1>".getBytes();
                        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
                        response.content().writeBytes(content);
                        response.headers().set("Content-Length", content.length);
                        channelHandlerContext.writeAndFlush(response);
                    }
                });
            }
        });

        CountDownLatch countDownLatch = new CountDownLatch(2);

        new Thread(() -> {
            try {
                serverBootstrap.bind(8765).sync().channel().closeFuture().sync();
            } catch (InterruptedException exception) {
                log.error("绑定端口1异常：{}", exception.getMessage(), exception);
            } finally {
                countDownLatch.countDown();
            }
        }, "thread1").start();

        new Thread(() -> {
            try {
                serverBootstrap.bind(8900).sync().channel().closeFuture().sync();
            } catch (InterruptedException exception) {
                log.error("绑定端口2异常：{}", exception.getMessage(), exception);
            } finally {
                countDownLatch.countDown();
            }
        }, "thread2").start();

        countDownLatch.await();
        bossGroup.shutdownGracefully();
        workGroup.shutdownGracefully();
    }

}
