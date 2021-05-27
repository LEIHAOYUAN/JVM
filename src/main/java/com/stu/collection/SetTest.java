package com.stu.collection;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;

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
        Set<Long> unitCodeSet = Sets.newHashSet();
        unitCodeSet.add(null);
        unitCodeSet.add(null);
        unitCodeSet.add(90L);
        unitCodeSet.add(90L);
        log.info("set结果：{}", JSON.toJSONString(unitCodeSet));
    }


}
