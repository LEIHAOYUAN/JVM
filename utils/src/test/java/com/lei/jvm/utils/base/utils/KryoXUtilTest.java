package com.lei.jvm.utils.base.utils;

import com.alibaba.fastjson.JSON;
import com.lei.jvm.utils.base.utils.kryo.KryoXUtil;
import lombok.Data;
import org.junit.Test;

import java.io.Serializable;

/**
 * @author leihaoyuan
 * @version 2023/12/4 11:28
 */
public class KryoXUtilTest {


    @Test
    public void testSerialize() {
        byte[] serialize = KryoXUtil.serialize(buildStudent());
        System.out.println(("序列化结果=" + JSON.toJSON(serialize)));
    }


    @Test
    public void testDeserialize() {
        // byte[] bytes = new byte[]{};
        // byte[] bytes = new byte[]{1, 0, 99, 111, 109, 46, 108, 101, 105, 46, 106, 118, 109, 46, 117, 116, 105, 108, 115, 46, 98, 97, 115, 101, 46, 117, 116, 105, 108, 115, 46, 75, 114, 121, 111, 88, 85, 116, 105, 108, 84, 101, 115, 116, 36, 83, 116, 117, 100, 101, 110, -12, 1, 1, -58, 1, 1, -125, -27, -68, -96, -28, -72, -119};
        byte[] bytes = new byte[]{1, 0, 99, 111, 109, 46, 108, 101, 105, 46, 106, 118, 109, 46, 117, 116, 105, 108, 115, 46, 98, 97, 115, 101, 46, 117, 116, 105, 108, 115, 46, 75, 114, 121, 111, 88, 85, 116, 105, 108, 84, 101, 115, 116, 36, 83, 116, 117, 100, 101, 110, -12, 1, 1, -58, 1, 1, -125, -27, -68, -96, -28, -72, -119, 1, 1};
        Student deserialize = KryoXUtil.deserialize(bytes, Student.class);
        System.out.println(("反序列化结果=" + JSON.toJSONString(deserialize)));
    }


    private Student buildStudent() {
        Student stu = new Student();
        stu.setName("张三");
        stu.setAge(99);
//        stu.setSex(true);
        return stu;
    }


    @Data
    public class Student implements Serializable {
        private static final long serialVersionUID = -8670690954203461108L;
        private String name;

        private Integer age;

//        private Boolean sex;
    }


}
