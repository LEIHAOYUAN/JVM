package com.stu.collection;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author leihaoyuan
 * @Date 2021/5/27 18:30
 * @Version 1.0
 * @Description
 */
@Slf4j
public class SetTest {

    public static void main(String[] args) {
        convertTypeArrayToSet();
    }

    private static void test(){
        Set<Long> unitCodeSet = Sets.newHashSet();
        unitCodeSet.add(null);
        unitCodeSet.add(null);
        unitCodeSet.add(90L);
        unitCodeSet.add(90L);
        log.info("set结果：{}", JSON.toJSONString(unitCodeSet));
    }

    private static void arrayToSet(){
        String[] strArray= new String[]{"1", "111", "2","21", "22", "222"};
        HashSet<String> strings = Sets.newHashSet(Arrays.asList(strArray));
        strings.add("AAA");
        log.info("结果：{}",JSON.toJSONString(strings));
    }

    private static void convertTypeArrayToSet(){
        String[] strArray= new String[]{"1", "111", "2","21", "22", "222"};
        HashSet<String> strings = Sets.newHashSet(Arrays.asList(strArray));
        strings.add("AAA");
        log.info("结果：{}",JSON.toJSONString(strings));
    }


}
