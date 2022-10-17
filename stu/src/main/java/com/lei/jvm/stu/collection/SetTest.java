package com.lei.jvm.stu.collection;

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
        setToArray();
    }

    private static void testSequence() {
        Set<Long> unitCodeSet = Sets.newHashSet();
        unitCodeSet.add(5L);
        unitCodeSet.add(null);
        unitCodeSet.add(null);
        unitCodeSet.add(90L);
        unitCodeSet.add(60L);
        unitCodeSet.add(569L);
        unitCodeSet.add(23L);
        unitCodeSet.add(25L);
        unitCodeSet.add(230L);
        unitCodeSet.add(0L);
        unitCodeSet.add(1L);
        log.info("set结果：{}", JSON.toJSONString(unitCodeSet));
    }

    private static void setToArray(){
        Set<String> param = Sets.newHashSet();
        for (int i = 0; i < 5; i++) {
            param.add("A"+i);
        }
        log.info("转换为数组={}",JSON.toJSONString(param.toArray(new String[0])));
    }

    private static void arrayToSet() {
        String[] strArray = new String[]{"1", "111", "2", "21", "22", "222"};
        HashSet<String> strings = Sets.newHashSet(Arrays.asList(strArray));
        strings.add("AAA");
        log.info("结果：{}", JSON.toJSONString(strings));
    }

    private static void convertTypeArrayToSet() {
        String[] strArray = new String[]{"1", "111", "2", "21", "22", "222"};
        HashSet<String> strings = Sets.newHashSet(Arrays.asList(strArray));
        strings.add("AAA");
        log.info("结果：{}", JSON.toJSONString(strings));
    }


}
