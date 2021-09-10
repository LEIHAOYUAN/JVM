package com.stu.serializable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Student1 implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private transient String password;
    private static int count = 0;
    private static final String TAG = "static-final";

    private HashMap<String, List<Integer>> maps = new HashMap<>();
    {
        maps.put("AAA",new ArrayList<>());
        maps.put("BBB",new ArrayList<>());
        maps.put("CCC",new ArrayList<>());
        maps.put("DDD",new ArrayList<>());
    }

    public Student1(String name, String password) {
        System.out.println("调用Student的带参构造方法 ");
        this.name = name;
        this.password = password;
        count++;
    }

    public String toString() {
        return "人数：" + count + "姓名：" + name + "密码：" + password+"   static-final:"+TAG+"   maps.size:"+maps.size();
    }
}