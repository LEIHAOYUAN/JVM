package com.lei.jvm.stu.annotation;

/**
 *  职能描述：
 *  @author leihaoyuan
 *  @version 2022/9/27 15:43
 */
public class AnnotationDemo {

    @MTCache(domain = "annotation", keys = {"#user.id", "#user.name"}, clazz = String.class)
    public void test() {

    }
}



