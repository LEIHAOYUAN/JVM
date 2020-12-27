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

        Student s3 = new Student();
        s3.setName("A");

        Student s4 = new Student();
        s4.setName("AB");

        List<Student> list = Lists.newArrayList();
        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(s4);
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
