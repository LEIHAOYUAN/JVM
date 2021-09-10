package com.lei.stu.javax;

import com.alibaba.fastjson.JSON;

import javax.validation.Valid;

/**
 * @Description javax validation demo
 * https://mp.weixin.qq.com/s/8O0v41b6PDbuW-Ugd-k9Sg
 * [官网]
 * http://hibernate.org/validator/documentation/getting-started/
 * @Author leihaoyuan
 * @Date 2020/3/13 16:23
 */
public class Test {

    public static void main(String[] args) {
        Student param = Student.builder().age(null).build();
        ValidationUtil.ValidResult validResult = ValidationUtil.validateBean(param);
//        new Test().testValidate(param);
        if(validResult.hasErrors()){
            String errors = validResult.getErrors();
            System.out.println(errors);
        }
    }

    public void testValidate(@Valid Student param) {
        System.out.println(JSON.toJSONString(param));
    }

}
