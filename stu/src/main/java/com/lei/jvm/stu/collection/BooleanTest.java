package com.lei.jvm.stu.collection;

import lombok.extern.slf4j.Slf4j;

/**
 * @author leihaoyuan
 * @version 2023/11/8 10:14
 */
@Slf4j
public class BooleanTest {

    public static void main(String[] args) {
        testEquals();
    }


    public static void testEquals(){
        Boolean param = null;
        log.info("测试结果={}",Boolean.FALSE.equals(param));
    }


}
