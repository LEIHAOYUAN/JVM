package com.stu.fastjson;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.TopicPartition;
import org.springframework.kafka.support.SendResult;

import java.util.List;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2020/10/12 12:03
 */
public class Test {


    public static void main(String[] args) {
        TestFastJson test = new TestFastJson();
        System.out.println(JSON.toJSONString(test));
    }

    private static void test01(){
        Student student = new Student();
        student.setAddress("XXXXX");
        student.setName("PPPPPPP");
        student.setAge(100);
        ProducerRecord<String, Student> producerRecord = new ProducerRecord<String, Student>("TEST", student);
        RecordMetadata recordMetadata = new RecordMetadata(new TopicPartition("TESt", 100), 0L, 0L, 0L, 0L, 0, 0);

        SendResult<String, Student> result = new SendResult<String, Student>(producerRecord, recordMetadata);

        System.out.println("方式1："+result.toString());
        System.out.println("方式2："+JSON.toJSONString(result));
        System.out.println("方式3："+JSON.toJSONString(result,true));
    }


    private static void testIgnore() {
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
        if (distictSize != listStudent.size()) {
            System.out.println("有重复数据");
        } else {
            System.out.println("不存在重复数据");
        }
    }

    @Ignore
    public static String test(Student student) {
        return student.getAddress();
    }


}
