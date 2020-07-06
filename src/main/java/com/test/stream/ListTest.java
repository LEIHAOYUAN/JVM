package com.test.stream;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description 合并指定属性
 * @Author leihaoyuan
 * @Date 2020/6/9 16:44
 */
public class ListTest {

    public static void main(String[] args) {
//        test01();

        test01_02();

    }

    private static void test01() {
        List<Student> list = Lists.newArrayList();
        list.add(new Student("张三", null, BigDecimal.TEN));
        List<Student> collect = list.stream().filter(item -> item.getName().equals("56")).collect(Collectors.toList());
        collect.forEach(item -> item.setAge("0"));
        collect.stream().filter(item -> item.getName().equals("XXX")).forEach(System.out::println);
        System.out.println(collect.size());

    }

    private static void test01_01() {
        List<Student> list = Lists.newArrayList();
        list.add(new Student("张三", null, BigDecimal.TEN));

        Student student = list.stream().filter(item -> "XXXX".equals(item.getName())).findFirst().orElse(list.get(0));

        System.out.println(student.getName());

    }

    private static void test01_02() {
        List<Student> list = Lists.newArrayList();
        list.add(new Student("张三", null, BigDecimal.TEN));
        list.add(new Student("张三", null, BigDecimal.TEN));

       int  count = list.stream().filter(item -> "张三1".equals(item.getName())).map(Student::getName).collect(Collectors.toSet()).size();

        System.out.println(count);

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
