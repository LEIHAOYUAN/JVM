package com.test.collection.iterator;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.test.stream.Student;

import java.util.Iterator;
import java.util.List;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2020/12/27 14:15
 */
public class ListTest {

    public static void main(String[] args) {
        test01();
    }

    private static void test(){
        Student s1 = new Student();
        s1.setName("AAA");

        Student s2 = new Student();
        s2.setName("AAA");

        Student s3 = new Student();
        s3.setName("BBB");

        List<Student> list = Lists.newArrayList();
        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.removeIf(item -> item.getName().equals("AAA"));

        System.out.println(JSON.toJSONString(list));
    }

    private static void test01(){
        Student s1 = new Student();
        s1.setName("AAA");

        Student s2 = new Student();
        s2.setName("AAA");

        Student s3 = new Student();
        s3.setName("BBB");

        List<Student> list = Lists.newArrayList();
        list.add(s1);
        list.add(s2);
        list.add(s3);
        Iterator<Student> iterator = list.iterator();
        while (iterator.hasNext()){
            Student item = iterator.next();
            if(item.getName().equals("AAA")){
                iterator.remove();
                System.out.println("已经移除的："+item.toString());
                System.out.println("集合大小："+list.size());
                continue;
            }
            System.out.println("循环结束");
        }

        System.out.println(JSON.toJSONString(list));
    }
}
