package com.lei.jvm.stu.serialization.json;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.lei.jvm.stu.serialization.SerializationInfo;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 *  职能描述：
 *  @author leihaoyuan
 *  @version 2022/9/19 14:27
 */

@Slf4j
public class FastJsonTest {

    public static void main(String[] args) {
        testParseObject();
    }

    private static void testParseObject() {
        HotKeyConfigDomain domain = new HotKeyConfigDomain();
        domain.setApplicationName("应用名");
        domain.setInterval(100);
        domain.setThreshold(20);
        List<HotKeyConfigDomain> list = Lists.newArrayList();
        list.add(domain);
        log.info(JSON.toJSONString(list));

    }

    private static void testByte() {
        SerializationInfo info = buildObject();
        byte[] bytes = JSON.toJSONString(info).getBytes(StandardCharsets.UTF_8);
        String json = new String(bytes, StandardCharsets.UTF_8);
        log.info("字节数据转换JSON={}", json);
        SerializationInfo serializationInfo = JSON.parseObject(json, SerializationInfo.class);
        log.info("转换对象={}", serializationInfo.getName());
    }

    private static void testByteWithNull() {
        String json = new String(null, StandardCharsets.UTF_8);
        SerializationInfo serializationInfo = JSON.parseObject(json, SerializationInfo.class);
        log.info("转换结果={}", JSON.toJSONString(serializationInfo));
    }

    private static SerializationInfo buildObject() {
        SerializationInfo info = new SerializationInfo(100L, "hessian", BigDecimal.TEN);
        info.setName("AAA");
        return info;
    }


}
