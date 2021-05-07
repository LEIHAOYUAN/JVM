package com.stu.stream;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description 集合测试
 * @Author leihaoyuan
 * @Date 2020/6/9 16:44
 */
@Slf4j
public class ListTest {

    public static void main(String[] args) throws InterruptedException {
        testSort();
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

    private static void test01() {
        List<Student> list = Lists.newArrayList();
        list.add(new Student("张三", null, BigDecimal.TEN));
        List<Student> collect = list.stream().filter(item -> item.getName().equals("56")).collect(Collectors.toList());
        collect.forEach(item -> item.setAge("0"));
        collect.stream().filter(item -> item.getName().equals("XXX")).forEach(System.out::println);
        System.out.println(collect.size());

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
