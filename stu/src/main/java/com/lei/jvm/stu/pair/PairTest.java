package com.lei.jvm.stu.pair;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.lei.jvm.stu.clone.Student;
import com.lei.jvm.utils.base.pair.Pair;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class PairTest {
    public static void main(String[] args) {
        Pair<String, String> stringPair = Pair.of("key", "value");
        log.info(stringPair.getKey() + ":" + stringPair.getValue());

        Pair<Long, List<Student>> listPair = Pair.of(1L, Lists.newArrayList(new Student(1), new Student(2)));
        log.info("page-pair={}", JSON.toJSON(listPair));
    }


}
