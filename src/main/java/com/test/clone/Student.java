package com.test.clone;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Constructor;

@Getter
@Setter
public class Student implements Cloneable {

    private int id;

    public Student(Integer id) {
        this.id = id;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        return super.clone();
    }

    public static void main(String[] args) throws Exception {

        Constructor<Student> constructor = Student.class
                .getConstructor(Integer.class);
        Student stu3 = constructor.newInstance(123);
        Student stu4 = (Student) stu3.clone();
        Student stu5 = (Student) stu3.clone();
        System.out.println("stu3:"+stu3);
        System.out.println("stu4:"+stu4);
        System.out.println("stu5:"+stu5);

        System.out.println("stu3.hashCode:"+stu3.hashCode());
        System.out.println("stu4.hashCode:"+stu4.hashCode());
        System.out.println("stu5.hashCode:"+stu5.hashCode());
    }
}