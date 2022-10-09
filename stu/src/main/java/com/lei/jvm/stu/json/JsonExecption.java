package com.lei.jvm.stu.json;

import com.alibaba.fastjson.JSON;
import com.lei.jvm.stu.clone.Student;
import lombok.extern.slf4j.Slf4j;

/**
 *  职能描述：测试JSON转换异常
 *  @author leihaoyuan
 *  @version 2022/7/27 10:31
 */
@Slf4j
public class JsonExecption {


    public static void main(String[] args) {
        String json = null;
        Student student = JSON.parseObject(json, Student.class);
        log.info("转换结果：{}", JSON.toJSONString(student));
    }

}
