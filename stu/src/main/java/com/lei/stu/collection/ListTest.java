package com.lei.stu.collection;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.lei.stu.clone.Student;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2020/11/23 15:27
 */
@Slf4j
public class ListTest {

    public static void main(String[] args) {
        testFilter();
    }


    private static void testFilter(){
        Student s1 = new Student(200,null,null);
        Student s2 = new Student(300,null,null);
        Student s3 = new Student(400,null,null);
        List<Student> param = Lists.newArrayList();
        param.add(s1);
        param.add(s2);
        param.add(s3);
        BigDecimal total = param.stream().filter(i->null != i.getAmount() && null != i.getVolume())
                .reduce(new BigDecimal(0),(x,y)->x.add(y.getVolume().multiply(y.getAmount()).divide(BigDecimal.TEN,7, RoundingMode.HALF_UP)),BigDecimal::add);

        log.info("汇总总数量：{}",JSON.toJSONString(total));

    }


    private static void testSplit() {
        List<Long> param = Lists.newArrayList();
        for (int i = 0; i < 800; i++) {
            param.add(i * 1L);
        }
        int size = param.size() / 3 + 1;
        List<List<Long>> partition = ListUtils.partition(param, size);
        log.info("分组大小：{}，分组结果：{}", size, JSON.toJSONString(partition));
    }

    private static void testToMap() {
        List<Student> listStu = Lists.newArrayList();
//        listStu.add(new Student(null));
//        listStu.add(new Student(1));

        Map<Integer, Student> collect = listStu.stream().collect(Collectors.toMap(Student::getId, s -> s));
        System.out.println(collect == null);
        System.out.println(JSON.toJSONString(collect));
    }


    private static void testRemove1() {
        List<String> param = Lists.newArrayList(Arrays.asList("AAA", "AA", "ABEC"));
        log.info("移除前：{}", JSON.toJSONString(param));
        Iterator<String> iterator = param.iterator();
        while (iterator.hasNext()) {
            if ("AAA".equals(iterator.next())) {
                iterator.remove();
            }
        }
        log.info("移除后：{}", JSON.toJSONString(param));
    }

    private static void testRemove2() {
        List<String> param = Lists.newArrayList(Arrays.asList("A", "AAA", "GDFES"));
        log.info("移除前：{}", JSON.toJSONString(param));
        param.removeIf("AAA"::equals);
        log.info("移除后：{}", JSON.toJSONString(param));
    }

    private static void testSort() {
        List<Long> list = Lists.newArrayList();
        list.add(null);
        list.add(null);
        list.add(20L);
        list.add(8L);
        list.add(1L);
        list.add(56L);
        list.add(-3L);
        log.info("排序前：{}", JSON.toJSONString(list));
        if (list.contains(null)) {
            log.info("包含空值，无法排序");
        }
        list = list.stream().filter(Objects::nonNull).collect(Collectors.toList());
        Collections.sort(list);
        log.info("排序后：{}", JSON.toJSONString(list));
    }

    private static void testDistinct() {
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
    private static void testDealMapDuplicateKey() {
        List<Student> listStu = Lists.newArrayList();
        listStu.add(null);
//        listStu.add(new Student(null));
        listStu.add(new Student(6));
//        listStu.add(new Student(8));
//        listStu.add(new Student(10));
//        listStu.add(new Student(11));
//        listStu.add(new Student(12));
        listStu = listStu.stream().filter(Objects::nonNull).collect(Collectors.toList());
        Map<Integer, Student> idMap = listStu.stream().collect(Collectors.toMap(Student::getId, s -> s, (oldValue, newValue) -> newValue));
        System.out.println(JSON.toJSONString(idMap));
    }

    private static void testMapDuplicateKey() {
        List<Student> listStu = Lists.newArrayList();
        listStu.add(new Student(8));
        listStu.add(new Student(10));
        listStu.add(new Student(11));
        listStu.add(new Student(12));
        Map<Integer, Student> idMap = listStu.stream().collect(Collectors.toMap(Student::getId, s -> s));
        System.out.println(JSON.toJSONString(idMap));
    }

    private static void testModifyStream() {
        List<Student> listStu = Lists.newArrayList();
        listStu.add(new Student(10));
        Map<Integer, Student> idMap = listStu.stream().collect(Collectors.toMap(Student::getId, s -> s));

        idMap.get(10).setId(500);
        log.info("修改后集合：{}", JSON.toJSONString(listStu));
    }


    /**
     * 根据一个集合过滤另一个集合
     */
    private static void testCollectionInster() {
        List<Integer> conditionList = Lists.newArrayList();
        conditionList.add(10);
        conditionList.add(null);


        List<Student> listStu = Lists.newArrayList();
        listStu.add(new Student(10));
        listStu.add(new Student(10));
        listStu.add(new Student(11));
        listStu.add(new Student(12));

        System.out.println("过滤前：" + JSON.toJSONString(listStu));

        listStu = listStu.stream().filter(item -> {
            return !conditionList.contains(item.getId());
        }).collect(Collectors.toList());

        System.out.println("过滤后：" + JSON.toJSONString(listStu));
    }
}
