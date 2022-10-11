package com.lei.jvm.stu.serialization.json;

import com.alibaba.fastjson.JSON;
import com.lei.jvm.stu.serialization.SerializationInfo;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

/**
 *  职能描述：
 *  @author leihaoyuan
 *  @version 2022/9/19 14:27
 */

@Slf4j
public class FastJsonTest {

    public static void main(String[] args) {
        testByte();
    }

    private static void testParseObject() {
        SerializationInfo info = buildObject();
        log.info("json格式：{}", JSON.toJSONString(info));

        String demoJson = "{\"name\":\"AAA\"}";
        SerializationInfo o = JSON.parseObject(demoJson, SerializationInfo.class);
        log.info("JSON反序列化:{}", o.getId());
    }

    private static void testByte() {
        SerializationInfo info = buildObject();
        byte[] bytes = JSON.toJSONString(info).getBytes(StandardCharsets.UTF_8);
        String json = new String(bytes, StandardCharsets.UTF_8);
        log.info("字节数据转换JSON={}", json);
        SerializationInfo serializationInfo = JSON.parseObject(json, SerializationInfo.class);
        log.info("转换对象={}", serializationInfo.getName());

    }

    private static SerializationInfo buildObject() {
        SerializationInfo info = new SerializationInfo(100L, "hessian", BigDecimal.TEN);
        info.setName("AAA");
        return info;
    }


}
