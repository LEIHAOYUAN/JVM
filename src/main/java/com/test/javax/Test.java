package com.test.javax;

import com.alibaba.fastjson.JSON;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2020/3/13 16:23
 */
public class Test {

    public static void main(String[] args) {

        Student param = new Student();
        new Test().testValidate(param);

    }

    //    public void testValidate(@Valid Student param) {
//        System.out.println(JSON.toJSONString(param));
//    }
    public void testValidate(Student param) {
        System.out.println(JSON.toJSONString(param));
    }

}
