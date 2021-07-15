package com.stu.string;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author leihaoyuan
 * @Date 2021/7/15 10:11
 * @Version 1.0
 * @Description 字符串
 */
@Slf4j
public class StringTest {

    public static void main(String[] args) {
        testReplace();
    }

    private static void testSplit() {
        String param = "TEST,e  ,erfef,eef,tt,fs  ,,99,,测试  ";
        String[] split = param.split(",");
        log.info("切分数组长度：{}",split.length);
        for (String s : split) {
            log.info("切割后结果：{}", s.length());
        }
    }

    private static void testReplace(){
        String param = "TEST,e  ,erfef,eef,tt,fs  ,T,99,,测试  ";
        String replace = param.replace("T", "它");
        log.info("替换前：{}",param);
        log.info("替换后：{}",replace);

    }


}
