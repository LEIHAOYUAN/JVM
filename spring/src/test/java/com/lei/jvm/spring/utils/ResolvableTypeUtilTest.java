package com.lei.jvm.spring.utils;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.lei.jvm.json.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author leihaoyuan
 * @version 2023/11/1 13:41
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ResolvableTypeUtilTest {


    public List<List<Student>> listStudent() {
        Student s1 = new Student("AAA");
        Student s2 = new Student("AAA");
        Student s3 = new Student("AAA");

        List<Student> students = Lists.newArrayList(s1, s2, s3);
        List<List<Student>> result = Lists.newArrayList();
        result.add(students);
        return result;
    }


    @Test
    public void testGetMethodReturnType() throws NoSuchMethodException {
        List<List<Student>> data = listStudent();
        Type type = ResolvableTypeUtil.getMethodReturnType(ResolvableTypeUtilTest.class.getDeclaredMethod("listStudent"));

        String jsonString = JSON.toJSONString(data);
        List<List<Student>> object = (List<List<Student>>)JSON.parseObject(jsonString, type);
        log.info("方法返回值类型={}", type);
        log.info("方法返回值序列化={}", jsonString);
        log.info("方法返回值反序列化={}", object);
    }


}
