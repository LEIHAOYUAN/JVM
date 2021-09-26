package com.lei.stu.base;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author leihaoyuan
 * @Date 2021/9/6 11:00
 * @Version 1.0
 * @Description 字符串类型测试
 */
@Slf4j
public class StringTest {

    public static void main(String[] args) {
        testSplit();
    }


    public static void testSplit(){
        String param = "http://www.baidu.comhttp://www.alibaba.com888,558";
        String[] urls = param.split(",");
        for (String url : urls) {
            log.info("拆分URL：{}",url);
        }
    }

}
