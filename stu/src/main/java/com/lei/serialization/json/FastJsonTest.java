package com.lei.serialization.json;

import com.alibaba.fastjson.JSON;
import com.lei.serialization.SerializationInfo;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 *  职能描述：
 *  @author leihaoyuan
 *  @version 2022/9/19 14:27
 */

@Slf4j
public class FastJsonTest {

    public static void main(String[] args) {
        SerializationInfo info = new SerializationInfo(100L, "hessian", BigDecimal.TEN);
        info.setName("AAA");
        log.info("json格式：{}", JSON.toJSONString(info));

        String demoJson = "{\"name\":\"AAA\"}";
        SerializationInfo o = JSON.parseObject(demoJson, SerializationInfo.class);
        log.info("JSON反序列化:{}",o.getId());
    }




}
