package com.lei.stu.base;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author leihaoyuan
 * @Date 2021/9/6 11:00
 * @Version 1.0
 * @Description 字符串类型测试
 */
@Slf4j
public class StringTest {

    public static void main(String[] args) {
        testValueOf();
    }


    public static void testValueOf() {
        log.info("null转换字符串：{}", String.valueOf(null));
    }


    public static void testNumStr() {
        Long param = 123456L;
        String str = param.toString();
        log.info("数字长度：{}", str.length());
    }


    public static void testPlus() {
        log.info("" + null);
    }

    public static void testJoin() {
        List<String> param = Lists.newArrayList();
        param.add("");
        param.add("www.baidu.com");
        param.add("   ");
        param.add("www.jd.com");
        param = param.stream().filter(StringUtils::isNotBlank).collect(Collectors.toList());
        log.info("过滤结果：{}", JSON.toJSONString(param));
        String collect = param.stream().map(String::valueOf).collect(Collectors.joining(","));
        log.info("逗号拼接结果：{}", collect);
    }


    public static void testSplit() {
        String param = "http://www.baidu.comhttp://www.alibaba.com888,558";
        String[] urls = param.split(",");
        for (String url : urls) {
            log.info("拆分URL：{}", url);
        }
    }

}
