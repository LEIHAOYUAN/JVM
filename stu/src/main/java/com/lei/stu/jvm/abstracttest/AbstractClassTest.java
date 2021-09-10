package com.lei.stu.jvm.abstracttest;

import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2020/12/11 13:17
 */
public abstract class AbstractClassTest {

    private static final List<WeakReference<AbstractClassTest>> LISTENERS = Collections.synchronizedList(new LinkedList<>());

    public AbstractClassTest(){
        LISTENERS.add(new WeakReference<>(this));
    }

    public static void main(String[] args) {
        System.out.println(LISTENERS.size());
    }


    private void test(){
        System.out.println("test....");
    }

    abstract void aTtest();
}
