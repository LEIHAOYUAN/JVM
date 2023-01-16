//package com.lei.jvm.spring.webflux;
//
//import io.netty.channel.ChannelOption;
//import io.netty.handler.ssl.SslContextBuilder;
//import io.netty.handler.timeout.ReadTimeoutHandler;
//import io.netty.handler.timeout.WriteTimeoutHandler;
//import lombok.experimental.UtilityClass;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.client.reactive.ReactorClientHttpConnector;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.ipc.netty.http.client.HttpClient;
//import reactor.ipc.netty.resources.LoopResources;
//
//
//import java.io.File;
//import java.util.concurrent.TimeUnit;
//
///**
// *  职能描述：
// *  @author leihaoyuan
// *  @version 2023/1/16 17:13
// */
//@Slf4j
//@UtilityClass
//public class WebClientUtil {
//
//
//    public static WebClient getHttpClient() {
//        //配置动态连接池
//        //ConnectionProvider provider = ConnectionProvider.elastic("elastic pool");
//        //配置固定大小连接池，如最大连接数、连接获取超时、空闲连接死亡时间等
//        ConnectionProvider provider = ConnectionProvider.create("fixed", 45);
//        HttpClient httpClient = HttpClient.create(provider)
//                .secure(sslContextSpec -> {
//                    SslContextBuilder sslContextBuilder = SslContextBuilder.forClient()
//                            .trustManager(new File("E://server.truststore"));
//                    sslContextSpec.sslContext(sslContextBuilder);
//                }).tcpConfiguration(tcpClient -> {
//                    //指定Netty的select 和 work线程数量
//                    LoopResources loop = LoopResources.create("kl-event-loop", 1, 4, true);
//                    return tcpClient.doOnConnected(connection -> {
//                                //读写超时设置
//                                connection.addHandlerLast(new ReadTimeoutHandler(10, TimeUnit.SECONDS))
//                                        .addHandlerLast(new WriteTimeoutHandler(10));
//                            })
//                            //连接超时设置
//                            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
//                            .option(ChannelOption.TCP_NODELAY, true)
//                            .runOn(loop);
//                });
//
//        return WebClient.builder()
//                .clientConnector(new ReactorClientHttpConnector(httpClient))
//                .build();
//
//    }
//
//
//}
