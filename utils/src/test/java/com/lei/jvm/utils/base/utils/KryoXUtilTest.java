package com.lei.jvm.utils.base.utils;

import com.alibaba.fastjson.JSON;
import com.lei.jvm.utils.base.utils.kryo.KryoXUtil;
import lombok.Data;
import org.junit.Test;

/**
 * @author leihaoyuan
 * @version 2023/12/4 11:28
 */
public class KryoXUtilTest {


    @Test
    public void testSerialize() {
        byte[] serialize = KryoXUtil.serialize(buildStudent());
        System.out.println(("序列化结果=" + JSON.toJSONString(serialize)));
    }


    @Test
    public void testDeserialize() {
        Student deserialize = KryoXUtil.deserialize(KryoXUtil.serialize(buildStudent()), Student.class);
        System.out.println(("反序列化结果=" + JSON.toJSONString(deserialize)));
    }


    private Student buildStudent() {
        Student stu = new Student();
        stu.setName("张三");
        stu.setAge(99);
        return stu;
    }


    @Data
    public class Student {
        private String name;

        private Integer age;
    }


}
