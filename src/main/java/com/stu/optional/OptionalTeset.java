package com.stu.optional;

import java.util.List;
import java.util.Optional;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2020/10/9 15:47
 */
public class OptionalTeset {


    public static void main(String[] args) {
        test01();
    }


    private static void test01(){
//        List<Person> list = Lists.newArrayList();
        List<Person> list = null;
//        list.add(new Person("zhangdan",90));
//        list.add(new Person("lsihi",90));

//        System.out.println(list.stream().findFirst());


        Optional.ofNullable(list).orElseThrow(() -> new RuntimeException());


    }
}
