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
        Student s1 = new Student();
        s1.setName("AAA");

        Student s2 = new Student();
        s2.setName("AAA");

        List<Student> list = Lists.newArrayList();
        list.add(s1);
        list.add(s2);
        list.removeIf(item -> item.getName().equals("AAA"));

        System.out.println(JSON.toJSONString(list));

    }

    private void test01(){
        Student s1 = new Student();
        s1.setName("AAA");

        Student s2 = new Student();
        s2.setName("AAA");

        List<Student> list = Lists.newArrayList();
        list.add(s1);
        list.add(s2);
        Iterator<Student> iterator = list.iterator();
        while (iterator.hasNext()){
            Student item = iterator.next();
            if(item.getName().equals("AAA")){
                iterator.remove();
            }
        }

        System.out.println(JSON.toJSONString(list));
    }
}
