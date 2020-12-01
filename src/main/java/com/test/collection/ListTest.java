package com.test.collection;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.test.clone.Student;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2020/11/23 15:27
 */
public class ListTest {

    public static void main(String[] args) {
        testDealMapDuplicateKey();
    }

    private static void testDealMapDuplicateKey(){
        List<Student> listStu = Lists.newArrayList();
        listStu.add(new Student(10));
        listStu.add(new Student(10));
        listStu.add(new Student(11));
        listStu.add(new Student(12));
        Map<Integer, Student> idMap = listStu.stream().collect(Collectors.toMap(Student::getId, s -> s,(oldValue, newValue) -> newValue));
        System.out.println(JSON.toJSONString(idMap));
    }

    private static void testMapDuplicateKey(){
        List<Student> listStu = Lists.newArrayList();
        listStu.add(new Student(10));
        listStu.add(new Student(10));
        listStu.add(new Student(11));
        listStu.add(new Student(12));
        Map<Integer, Student> idMap = listStu.stream().collect(Collectors.toMap(Student::getId, s -> s));
        System.out.println(JSON.toJSONString(idMap));
    }



    /**
     * 根据一个集合过滤另一个集合
     */
    private static void testCollectionInster(){
        List<Integer> conditionList = Lists.newArrayList();
        conditionList.add(10);
        conditionList.add(null);


        List<Student> listStu = Lists.newArrayList();
        listStu.add(new Student(10));
        listStu.add(new Student(10));
        listStu.add(new Student(11));
        listStu.add(new Student(12));

        System.out.println("过滤前："+ JSON.toJSONString(listStu));

        listStu = listStu.stream().filter(item -> {return !conditionList.contains(item.getId());}).collect(Collectors.toList());

        System.out.println("过滤后："+ JSON.toJSONString(listStu));
    }
}
