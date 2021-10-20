package com.lei.stu.stream;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Description 集合测试
 * @Author leihaoyuan
 * @Date 2020/6/9 16:44
 */
@Slf4j
public class ListTest {

    private static List<List<Integer>> subListData = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        testAddAll();
    }

    private static void testAddAll() {
        List<Long> param = Lists.newArrayList();
        param.addAll(Lists.newArrayList());
        param.addAll(Lists.newArrayList());
        param.addAll(Lists.newArrayList());
        param.addAll(Lists.newArrayList());
        log.info("集合大小：{}", param.size());
    }


    private static void testContains() {
        List<Long> param = Lists.newArrayList();
        param.add(null);
        log.info("集合是否为空：{}", CollectionUtils.isEmpty(param));
        log.info("测试集合包含：{}", param.contains(null));
    }


    private static void testSimpleSort() {
        List<Long> list = Lists.newArrayList();
        list.add(null);
        list.add(100L);
        list.add(19L);
        list.add(0L);
        log.info("排序前：{}", JSON.toJSONString(list));

        List<Long> collect = list.stream().filter(Objects::nonNull).sorted().collect(Collectors.toList());
        log.info("排序后：{}", JSON.toJSONString(collect));

    }

    /**
     * List.subList 进行切片操作导致OOM
     * 原因：
     * 循环中的 1000 个具有 10 万个元素的 List 始终得不到回收，因为它始终被 subList 方法返回的 子list 强引用
     */
    private static void subListOOM() {
        for (int i = 0; i < 1000; i++) {
            List<Integer> collect = IntStream.rangeClosed(1, 100000).boxed().collect(Collectors.toList());
            subListData.add(collect.subList(0, 1));
        }
    }

    private static void testDistinct() {
        List<Student> list = Lists.newArrayList();
        Student s1 = new Student();
        s1.setName("AAA");
        Student s2 = new Student();
        s2.setName("AAA");
        Student s3 = new Student();
        s3.setName("BBB");

        list.add(s1);
        list.add(s2);
        list.add(s3);

        List<String> collect = list.stream().map(Student::getName).distinct().collect(Collectors.toList());
        System.out.println(collect);
    }

    private static void tesSum() {
        List<Student> list = Lists.newArrayList();
        Student s1 = new Student();
        s1.setScore(BigDecimal.TEN.negate());
        Student s2 = new Student();
        s2.setScore(BigDecimal.TEN);
        Student s3 = new Student();
        s3.setScore(BigDecimal.ONE);

        list.add(s1);
        list.add(s2);
        list.add(s3);

        double sum = list.stream().mapToDouble(i -> i.getScore().doubleValue()).sum();
        System.out.println(sum);
    }

    private static void testFilter() {
        List<String> regionList = Lists.newArrayList();
        List<String> collect = regionList.stream().filter(item -> "99".equals(item)).collect(Collectors.toList());
        System.out.println(collect.size());

        List<String> collect1 = collect.stream().filter(item -> "111".equals(item)).collect(Collectors.toList());
        System.out.println(collect1.size());

        System.out.println("MAIN------start");
        System.out.println(1 / 0);
        System.out.println("MAIN------end");
    }

    private static void testSort() {
        List<Student> list = Lists.newArrayList();
        list.add(new Student("张三", "16", BigDecimal.TEN));
        list.add(new Student("张三", "16", BigDecimal.ZERO));
        list.add(new Student("张三", "16", BigDecimal.TEN));
        list.add(new Student("李四", "16", BigDecimal.ONE));
        log.info("排序前：{}", JSON.toJSONString(list));
        List<Student> collect = list.stream().sorted(Comparator.comparing(Student::getScore)).collect(Collectors.toList());
        log.info("排序后：{}", JSON.toJSONString(collect));
    }


    private static void testSplit() {
        String notes = "1,2   ,3,  ,5,6  ";
        if (StringUtils.isNotBlank(notes)) {
            List<String> listNos = Arrays.stream(notes.split(",")).filter(StringUtils::isNotBlank).map(String::trim).collect(Collectors.toList());
            System.out.println(Arrays.toString(listNos.toArray()));
        }

    }

    private static void testContins() {
        List<String> list = Lists.newArrayList();
        list.add("AAAA");
        list.add("BBB");
        boolean ccc = list.contains("AAA");
        System.out.println(ccc);
    }


    private static void test001() {
        List<String> aa = Lists.newArrayList();
        aa.add("AAA");
        aa.add("AAA");
        aa.add("AAA");
        aa.add("AAA");
        aa.add("AAA");
        aa.add("AAA");

        aa = aa.stream().filter(item -> item.equals("$$$")).collect(Collectors.toList());
        aa.forEach(item -> item += "BBB");
        System.out.println(aa.size());
    }

    private static void testAddObject() {
        List<Student> sumList = Lists.newArrayList();
        List<Student> list = Lists.newArrayList();
        Student stu = new Student("张三", null, BigDecimal.TEN);
        Student newStu = new Student();
        newStu = stu;
        sumList.add(stu);
        newStu.setName("李四");
        list.add(newStu);
        sumList.addAll(list);
        log.info("list添加重复对象：{}", JSON.toJSONString(list));
        log.info("sumList添加重复对象：{}", JSON.toJSONString(sumList));
    }

    private static void test01() {
        List<Student> list = Lists.newArrayList();
        list.add(new Student("张三", null, BigDecimal.TEN));
        List<Student> collect = list.stream().filter(item -> item.getName().equals("56")).collect(Collectors.toList());
        collect.forEach(item -> item.setAge("0"));
        collect.stream().filter(item -> item.getName().equals("XXX")).forEach(System.out::println);
        System.out.println(collect.size());

    }

    private static void testDefaultProperties() {
        Man man = new Man();
        man.setIsMan(false);
        log.info("校验对象默认值：{}", JSON.toJSONString(man));

    }


    private static void test02() {

        List<Student> list = Lists.newArrayList();
        list.add(new Student("张三", "16", BigDecimal.TEN));
        list.add(new Student("张三", "16", BigDecimal.TEN));
        list.add(new Student("张三", "16", BigDecimal.TEN));
        list.add(new Student("李四", "16", BigDecimal.ONE));

        List<Student> sumStudentList1 = Lists.newArrayList();


        Map<String, List<Student>> collect = list.parallelStream().collect(Collectors.groupingBy(Student::getName, Collectors.toList()));
        for (Map.Entry<String, List<Student>> entry : collect.entrySet()) {
            String s = entry.getKey();
            List<Student> students = entry.getValue();
            students.stream().reduce((student, student2) -> {
                student.setScore(student.getScore().add(student2.getScore()));
                return student;
            }).ifPresent(sumStudentList1::add);
        }

        System.out.println("原始：" + list);
        System.out.println("新：" + sumStudentList1);
        System.out.println("===============================================================================");
        List<Student> sumStudentList2 = Lists.newArrayList();
        list.parallelStream().collect(Collectors.groupingBy(Student::getName, Collectors.toList()))
                .forEach((s, students) ->
                        students.stream().reduce((student, student2) ->
                        {
                            student.setScore(student.getScore().add(student2.getScore()));
                            return student;
                        }).ifPresent(sumStudentList2::add)
                );

        System.out.println(sumStudentList2);
    }

}
