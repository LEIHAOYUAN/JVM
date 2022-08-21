package com.lei.stu.cast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.lei.stu.clone.Student;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * @Author leihaoyuan
 * @Date 2021/2/7 16:40
 * @Version 1.0
 * @Description
 */
@Slf4j
public class Test {

    public static void main(String[] args) {
        Object obj = Lists.newArrayList("AAA", "BBB", 888, new Student(999));

        List list = (List) obj;
        for (Object o : list) {
            log.info("遍历：{}", JSON.toJSONString(o));
        }
        log.info("----------------------------------------------------------");
        Student student = new Student(999);
        student.setStudent(new Student(88888));
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(student));
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            log.info("键：{}，值：{}", entry.getKey(), JSON.toJSONString(entry.getValue()));
        }
    }


}
