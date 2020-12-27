package com.test.stream.groupby;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.test.stream.Student;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2020/12/27 14:39
 */
public class GroupTest {

    public static void main(String[] args) {
        Student s1 = new Student();
        s1.setName("AAA");
        s1.setScore(BigDecimal.TEN);

        Student s2 = new Student();
        s2.setName("AAA");
        s2.setScore(BigDecimal.TEN);

        List<Student> list = Lists.newArrayList();
        list.add(s1);
        list.add(s2);

        List<Student> newList = Lists.newArrayList();
        list.stream().collect(Collectors.groupingBy(Student::getName, Collectors.toList())).forEach(
                (id, transfer) -> {
                    transfer.stream().reduce((a, b) -> {
                        Student ns = new Student();
                        ns.setAge(a.getAge());
                        ns.setName(a.getName());
                        ns.setScore(a.getScore().add(b.getScore()));
                        return ns;
                    }).ifPresent(newList::add);
                });
        System.out.println("相同名称分数累加：");
        System.out.println(JSON.toJSONString(newList));
    }
}
