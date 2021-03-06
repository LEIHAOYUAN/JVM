package com.stu.collection;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.stu.clone.Student;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2020/11/23 15:27
 */
@Slf4j
public class ListTest {

    public static void main(String[] args) {
        testSort();
    }

    private static void testToMap(){
        List<Student> listStu = Lists.newArrayList();
//        listStu.add(new Student(null));
//        listStu.add(new Student(1));

        Map<Integer, Student> collect = listStu.stream().collect(Collectors.toMap(Student::getId, s -> s));
        System.out.println(collect == null);
        System.out.println(JSON.toJSONString(collect));
    }

    private static void testSort(){
        List<Long> list = Lists.newArrayList();
        list.add(null);
        list.add(null);
        list.add(20L);
        list.add(8L);
        list.add(1L);
        list.add(56L);
        list.add(-3L);
        log.info("排序前：{}",JSON.toJSONString(list));
        if(list.contains(null)){
            log.info("包含空值，无法排序");
        }
        list = list.stream().filter(Objects::nonNull).collect(Collectors.toList());
        Collections.sort(list);
        log.info("排序后：{}",JSON.toJSONString(list));
    }

    private static void testDistinct(){
        List<Student> listStu = Lists.newArrayList();
        listStu.add(new Student(null));
        listStu.add(new Student(1));

        List<Integer> ids = listStu.stream().map(Student::getId).distinct().collect(Collectors.toList());
        System.out.println(JSON.toJSONString(ids));
        System.out.println(ids.size() == listStu.size());
    }


    /**
     * list转换map，key重复问题
     */
    private static void testDealMapDuplicateKey(){
        List<Student> listStu = Lists.newArrayList();
        listStu.add(null);
//        listStu.add(new Student(null));
        listStu.add(new Student(6));
//        listStu.add(new Student(8));
//        listStu.add(new Student(10));
//        listStu.add(new Student(11));
//        listStu.add(new Student(12));
        listStu = listStu.stream().filter(Objects::nonNull).collect(Collectors.toList());
        Map<Integer, Student> idMap = listStu.stream().collect(Collectors.toMap(Student::getId, s -> s,(oldValue, newValue) -> newValue));
        System.out.println(JSON.toJSONString(idMap));
    }

    private static void testMapDuplicateKey(){
        List<Student> listStu = Lists.newArrayList();
        listStu.add(new Student(8));
        listStu.add(new Student(10));
        listStu.add(new Student(11));
        listStu.add(new Student(12));
        Map<Integer, Student> idMap = listStu.stream().collect(Collectors.toMap(Student::getId, s -> s));
        System.out.println(JSON.toJSONString(idMap));
    }

    private static void testModifyStream(){
        List<Student> listStu = Lists.newArrayList();
        listStu.add(new Student(10));
        Map<Integer, Student> idMap = listStu.stream().collect(Collectors.toMap(Student::getId, s -> s));

        idMap.get(10).setId(500);
        log.info("修改后集合：{}",JSON.toJSONString(listStu));
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
