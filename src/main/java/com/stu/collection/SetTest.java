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
        arrayToSet();
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
        String[] strArray= new String[]{"Tom", "Bob", "Jane","Tom0", "Bob", "Jane"};
        HashSet<String> strings = Sets.newHashSet(Arrays.asList(strArray));
        strings.add("AAA");
        log.info("结果：{}",JSON.toJSONString(strings));
    }


}
