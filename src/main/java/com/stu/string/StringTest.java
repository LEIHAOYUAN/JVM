package com.stu.string;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringExclude;

/**
 * @Author leihaoyuan
 * @Date 2021/7/15 10:11
 * @Version 1.0
 * @Description 字符串
 */
@Slf4j
public class StringTest {

    public static void main(String[] args) {
        testContians();
    }


    private static void testContians(){
        String param = "2,3,4,6,7,8,10,11,12,13";
        log.info("测试包含：{}",param.contains("1"));
    }

    private static void testSplit() {
        String param = "TEST,e  ,erfef,eef,tt,fs  ,,99,,测试  ";
        String[] split = param.split(",");
        log.info("切分数组长度：{}",split.length);
        for (String s : split) {
            log.info("切割后结果：{}", s.length());
        }
    }

    private static void testSplitLastIndex(){
        StringBuilder builder = new StringBuilder("1,3,6,9,");

        int index = builder.lastIndexOf(",");
        if(index == builder.length()-1){
            builder.deleteCharAt(index);
        }
        log.info("处理结果：{}",builder.toString());

    }

    private static void testReplace(){
        String param = "TEST,e  ,erfef,eef,tt,fs  ,T,99,,测试  ";
        String replace = param.replace("T", "它");
        log.info("替换前：{}",param);
        log.info("替换后：{}",replace);

    }

    private static void testSubstring(){
        String param = "TEC20210506";
        log.info("字符串截取结果一：{}",param.substring("TEC".length()));
        log.info("字符串截取结果二：{}",param.substring("TEC".length(),4));
    }


}
