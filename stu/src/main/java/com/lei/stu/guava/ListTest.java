package com.lei.stu.guava;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2020/4/11 9:16
 */
public class ListTest {

    public static void main(String[] args) {
//        filterList();
        splitList();
        List<String> listParam = Lists.newArrayList();
    }

    /**
     * 切分集合
     */
    private static void splitList(){

    }

    /**
     * 集合过滤
     */
    private static void filterList() {
        Person p1 = new Person();
        p1.setAge(null);
        p1.setName("张三");
        p1.setMoney(200D);

        Person p2 = new Person();
        p2.setAge(20);
        p2.setName("李四");
        p2.setMoney(200D);

        Person p3 = new Person();
        p3.setAge(55);
        p3.setName("王五");
        p3.setMoney(0D);

        List<Person> listPersion = Lists.newArrayList();
        listPersion.add(p1);
        listPersion.add(p2);
        listPersion.add(p3);

        Collection<Person> filterRes = Collections2.filter(listPersion, new Predicate<Person>() {
            @Override
            public boolean apply(@Nullable Person person) {
                return null !=person.getAge() && person.getAge() > 0;
            }
        });

        ArrayList<Person> people = Lists.newArrayList(filterRes);
        System.out.println(JSON.toJSONString(people));
        System.out.println(people);
    }
}
