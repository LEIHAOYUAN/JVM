package com.lei.jvm.stu.serializable;

import java.io.*;

public class ObjectSerTest1 {
    public static void main(String args[]) {
        try {
            FileOutputStream fos = new FileOutputStream("test.obj");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            Student1 s1 = new Student1("张三", "123456");
            Student1 s2 = new Student1("王五", "56");

            oos.writeObject(s1);
            oos.writeObject(s2);

            oos.close();

            FileInputStream fis = new FileInputStream("test.obj");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Student1 s3 = (Student1) ois.readObject();
            Student1 s4 = (Student1) ois.readObject();

            System.out.println(s3);
            System.out.println("----------------------------------------");
            System.out.println(s4);

            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }
}