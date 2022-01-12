package com.lei.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.util.concurrent.DefaultThreadFactory;

/**
 * @Author leihaoyuan
 * @Date 2022/1/12 15:19
 * @Version 1.0
 * @Description 简单HTTPServer
 * 验证netty boosgroup实际线程数量问题：
 * https://zhuanlan.zhihu.com/p/378238584
 */
public class HttpServer {

    public static void main(String[] args) throws Exception {

        NioEventLoopGroup bossGroup = new NioEventLoopGroup(0, new DefaultThreadFactory("bossGroup"));
        NioEventLoopGroup workGroup = new NioEventLoopGroup(4, new DefaultThreadFactory("workGroup"));

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
        ChannelFuture channelFuture = serverBootstrap.bind(8085).sync();
        ChannelFuture closeFuture = channelFuture.channel().closeFuture();
        closeFuture.sync();

        bossGroup.shutdownGracefully();
        workGroup.shutdownGracefully();
    }

}
