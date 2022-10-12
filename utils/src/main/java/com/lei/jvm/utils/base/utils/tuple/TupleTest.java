package com.lei.jvm.utils.base.utils.tuple;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.javatuples.Triplet;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TupleTest {

    public static void main(String[] args) {
        List<Triplet<Class, String, String>> roleList = new ArrayList<Triplet<Class, String, String>>();

        /*
            三元组，存储数据：对应实体类字节码文件、数据表主键名称、数据表毕业院校字段名称
         */
        Triplet<Class, String, String> studentTriplet = TupleUtils.with(Student.class, "sid", "graduate");
        Triplet<Class, String, String> teacherTriplet = TupleUtils.with(Teacher.class, "tid", "graduate");
        Triplet<Class, String, String> programmerTriplet = TupleUtils.with(Programmer.class, "id", "graduate");

        roleList.add(studentTriplet);
        roleList.add(teacherTriplet);
        roleList.add(programmerTriplet);

        for (Triplet<Class, String, String> triplet : roleList) {
            System.out.println(triplet);
        }
    }

    @Data
    class Student {
        private String sid;
        private String graduate;
    }

    @Data
    class Teacher {
        private String sid;
        private String graduate;
    }

    @Data
    class Programmer {
        private String sid;
        private String graduate;
    }
}
