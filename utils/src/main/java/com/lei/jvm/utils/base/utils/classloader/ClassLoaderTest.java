package com.lei.jvm.utils.base.utils.classloader;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @Author leihaoyuan
 * @Date 2021/4/1 18:01
 * @Version 1.0
 * @Description 类加载器测试
 * https://my.oschina.net/u/267665/blog/3190533
 */
@Slf4j
public class ClassLoaderTest {

    public static void main(String[] args) {
        Object obj = "AAAA";
        log.info("结果：{}", JSON.toJSONString(obj));
    }

    private static void queryClassLoadTree() {
        ClassLoader myClassLoader = ClassLoaderTest.class.getClassLoader();
        log.info("当前类的类加载器名称：{}", myClassLoader.getClass().getName());

        ClassLoader fatherClassLoad = myClassLoader.getParent();
        log.info("父类加载器：{}", fatherClassLoad);

        log.info("爷爷类加载器：{}", fatherClassLoad.getParent());
    }

    /**
     * JNDI 服务利用线程上下文打破双亲委派机制
     * https://blog.csdn.net/P19777/article/details/100829154
     * 获取线程上下文类加载器，从而也就获得了应用程序类加载器（也可能是自定义的类加载器）
     * 从META-INF/services/java.sql.Driver文件中获取java.sql.Driver接口的实现类名“com.mysql.jdbc.Driver”
     * 通过线程上下文类加载器去加载这个Driver类，从而避开了双亲委派模型的弊端
     *
     * spi服务确确实实是破坏了双亲委派模型的，毕竟做到了父级类加载器加载了子级路径中的类
     *
     * https://blog.csdn.net/qq_18251707/article/details/104156040
     */
    private static void threadContextClassLoad() throws Exception {
        //
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "1234");

        Thread.currentThread().setContextClassLoader(ClassLoaderTest.class.getClassLoader());
    }


}
