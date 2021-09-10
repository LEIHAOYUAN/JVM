package com.lei.stu.jvm.refrece;

import com.lei.stu.clone.Student;

/**
 * @Description 引用类型赋值测试
 * @Author leihaoyuan
 * @Date 2020/12/15 13:18
 */
public class Test {

    public static void main(String[] args) {
        Student s1 = new Student(90);
        Student s2 = s1;
        System.out.println(s1.toString());
        System.out.println(s2.toString());
    }

}
