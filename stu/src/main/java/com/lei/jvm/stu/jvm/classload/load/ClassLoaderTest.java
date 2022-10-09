package com.lei.jvm.stu.jvm.classload.load;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author leihaoyuan
 * @Date 2022/1/27 13:39
 * @Version 1.0
 * @Description 类加载器与instanceof关键字演示
 */
@Slf4j
public class ClassLoaderTest {

    public static void main(String[] args) throws Exception {
        ClassLoader classLoader1 = ClassLoaderTest.class.getClassLoader();

        ClassLoader classLoader = new CustomerClassLoader();

        Object obj = classLoader.loadClass("com.lei.jvm.stu.jvm.classload.load.ClassLoaderTest").newInstance();
        log.info("{}", obj.getClass());
        log.info("{}", obj instanceof ClassLoaderTest);

        log.info("---------------------分隔符---------------------------");


    }


    private static ClassLoader customerClassLoader() {
        return new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null) {
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };
    }


    public static class CustomerClassLoader extends ClassLoader {
        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            try {
                String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                InputStream is = getClass().getResourceAsStream(fileName);
                if (is == null) {
                    return super.loadClass(name);
                }
                byte[] b = new byte[is.available()];
                is.read(b);
                return defineClass(name, b, 0, b.length);
            } catch (IOException e) {
                throw new ClassNotFoundException(name);
            }
        }
    }


}
