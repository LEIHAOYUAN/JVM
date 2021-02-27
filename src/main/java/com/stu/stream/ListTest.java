package com.stu.stream;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description 合并指定属性
 * @Author leihaoyuan
 * @Date 2020/6/9 16:44
 */
public class ListTest {

    public static void main(String[] args) throws InterruptedException {
//        test01();
//        test01_02();
//        long time1 = new Date().getTime();

//        System.out.println(time1);
//        Thread.sleep(5000);
//        long time2 = new Date().getTime();
//        System.out.println(time2);
//        System.out.println(time1 < time2);

//        test0002();
        Float aaa = 40.0000F;
        Double bbb = 40D;
        System.out.println(aaa.equals(bbb));
        System.out.println("------------------------");
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

        System.out.println("------------------------");
        System.out.println("------------------------");
        System.out.println("---------testcontains---------------");
        testContins();
        System.out.println("------------------------");
        System.out.println("------------------------");
        System.out.println("---------**************---------------");
        testSplit();

    }

    private static void testSplit() {
        String notes = "1,2   ,3,  ,5,6  ";
        if(StringUtils.isNotBlank(notes)){
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

    private static void test0001() {
        throw new RuntimeException("test");

    }

    private static void test0002() {
        test0001();
        System.out.println("test00002");
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
        list.add(new Student("张三", "fefe", BigDecimal.ZERO));

        int count = list.stream().filter(item -> "张三1".equals(item.getName())).map(Student::getName).collect(Collectors.toSet()).size();

        long countL = list.stream().filter(item -> "张三".equals(item.getName())).map(Student::getName).distinct().count();

        System.out.println(countL);

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
