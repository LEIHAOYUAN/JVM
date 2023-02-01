package com.lei.jvm.stu.collection.map.pojo;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 *  职能描述：
 *  @author leihaoyuan
 *  @version 2023/1/31 14:52
 */
@Slf4j
public class Test {

    public static void main(String[] args) {
//        parseStruct();
        log.info("比较结果={}","fff".equals(null));
    }

    private static void parseStruct() {
        Struct a = buildStruct("a");
        Struct a1 = buildStruct("a1");
        Struct a2 = buildStruct("a2");

        Struct a11 = buildStruct("a11");
        Struct a12 = buildStruct("a12");

        Struct a111 = buildStruct("a111");
        Struct a112 = buildStruct("a112");

        a.setChildren(Lists.newArrayList(a1, a2));
        a1.setChildren(Lists.newArrayList(a11, a12));
        a11.setChildren(Lists.newArrayList(a111, a112));

        log.info("json={}", JSON.toJSONString(a));
    }

    private static Struct buildStruct(String key) {
        Struct result = new Struct();
        result.setKey(key);
        result.setName("name是" + key);
        return result;
    }

    private static void parseExtendStruct() {
        ExtendStruct a = buildExtendStruct("a");
        ExtendStruct a1 = buildExtendStruct("a1");
        ExtendStruct a2 = buildExtendStruct("a2");

        ExtendStruct a11 = buildExtendStruct("a11");
        ExtendStruct a12 = buildExtendStruct("a12");

        ExtendStruct a111 = buildExtendStruct("a111");
        ExtendStruct a112 = buildExtendStruct("a112");

        a.setChildren(Lists.newArrayList(a1, a2));
        a1.setChildren(Lists.newArrayList(a11, a12));
        a11.setChildren(Lists.newArrayList(a111, a112));

        log.info("json={}", JSON.toJSONString(a));

        Map map = JSON.parseObject(JSON.toJSONString(a), Map.class);
        log.info("转换为map");
    }


    private static ExtendStruct buildExtendStruct(String name) {
        ExtendStruct result = new ExtendStruct();
        result.setName(name);
        result.setDefaultValue("默认值测试" + name);
        return result;
    }


}
