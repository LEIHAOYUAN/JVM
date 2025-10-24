package com.test.base;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

/**
 * @author ryan
 */
@Slf4j
public class IntStreamTest {

    public static void main(String[] args) {
        List<String> list = IntStream.range(0, 2).mapToObj(x -> UUID.randomUUID().toString()).toList();
        log.info("结果={}", JSON.toJSONString(list));
    }

}
