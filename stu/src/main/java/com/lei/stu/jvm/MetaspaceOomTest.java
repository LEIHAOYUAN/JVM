package com.lei.stu.jvm;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author leihaoyuan
 * @Date 2021/10/12 16:15
 * @Version 1.0
 * @Description java8 元空间内存溢出
 */
public class MetaspaceOomTest {

    public static void main(String[] args) {
        URL url = null;
        List<ClassLoader> classLoaderList = new ArrayList<ClassLoader>();
        try {
            url = new File("/tmp").toURI().toURL();
            URL[] urls = {url};
            while (true) {
                ClassLoader loader = new URLClassLoader(urls);
                classLoaderList.add(loader);
                loader.loadClass("com.lei.stu.jvm.Teset");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
