package com.lei.jvm.stu.base;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        List<String> labels = Lists.newArrayList();
        labels.add("包装精美");
        labels.add("口味适宜");
        log.info("JSON-ARRAY:{}",JSON.toJSONString(labels));

        log.info(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS").format(LocalDateTime.now()));

        String param = "中国";
        for (String s : param.split(",")) {
            log.info("切割结果：{}",s);
        }
    }

    private static String hide(String phoneNo) {
        if (StringUtils.isNotBlank(phoneNo)) {
            if (phoneNo.length() == 11) {
                return phoneNo.replaceAll("(\\w{3})\\w*(\\w{4})", "$1*****$2");
            }
        }
        return phoneNo;
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