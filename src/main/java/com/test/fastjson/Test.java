package com.test.fastjson;

import com.google.common.collect.Lists;
import jdk.nashorn.internal.ir.annotations.Ignore;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2020/10/12 12:03
 */
public class Test {


    public static void main(String[] args) {
        Student student = new Student();
        student.setAge(200);
        student.setName("TESTXXXX");
        student.setAddress("000000");

        Student student2 = new Student();
        student2.setAge(200);
        student2.setName("TESTXXX8X");
        student2.setAddress("000000");

        List<Student> listStudent = Lists.newArrayList();
        listStudent.add(student);
        listStudent.add(student2);
//        Map<String, String> collect = listStudent.stream().collect(Collectors.toMap(Student::getAddress, Student::getName));
//        System.out.println(collect.toString());

         int distictSize = (int) listStudent.stream().map(Student::getName).distinct().count();
         if(distictSize != listStudent.size()){
             System.out.println("有重复数据");
         }else{
             System.out.println("不存在重复数据");
         }
    }

    @Ignore
    public static String test(Student student) {
        return student.getAddress();
    }


}
