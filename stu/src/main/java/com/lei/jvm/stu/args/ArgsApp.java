package com.lei.jvm.stu.args;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @Author leihaoyuan
 * @Date 2021/5/23 18:18
 * @Version 1.0
 * @Description 可变参数传参测试
 */
@Slf4j
public class ArgsApp {

    public static void main(String[] args) {
        Object param = null;
        log.info("instanceof测试={}", param instanceof String);
    }

    private static void testParam() {
        String[] param = new String[]{"1", "2", "4", "0"};
        customArgs(param);
        log.info("集合传参测试");
        List<String> listParam = Lists.newArrayList();
        listParam.add("test1");
        listParam.add("test2");
        listParam.add("test3");
        customArgs(listParam);
    }


    public static void customArgs(Object... args) {
        for (Object item : args) {
            log.info("参数：{}", item);
        }
    }

}
