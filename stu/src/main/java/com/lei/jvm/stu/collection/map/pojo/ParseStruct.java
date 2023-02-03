package com.lei.jvm.stu.collection.map.pojo;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Stack;

@Slf4j
public class ParseStruct {


    public static void main(String[] args) {
        parseStruct();
    }

    private static void parseStruct() {
        Struct a = buildStruct("a");
        Struct a1 = buildStruct("a1");
        Struct a2 = buildStruct("a2");

        Struct a11 = buildStruct("a11");
        Struct a12 = buildStruct("a12");

        Struct a21 = buildStruct("a21");

        Struct a111 = buildStruct("a111");
        Struct a112 = buildStruct("a112");

        a.setChildren(Lists.newArrayList(a1, a2));
        a1.setChildren(Lists.newArrayList(a11, a12));
        a2.setChildren(Lists.newArrayList(a21));

        a11.setChildren(Lists.newArrayList(a111, a112));

        log.info("嵌套对象结构={}", JSON.toJSONString(a));

        Map<Object, Object> objectObjectMap = buildStructMap(a);

        log.info("解析结果={}", JSON.toJSONString(objectObjectMap));
    }

    private static Map<Object, Object> buildStructMap(Struct a) {
        Map<Object, Object> father = new LinkedHashMap<Object, Object>();

        Stack<Map<Object, Object>> stack = new Stack<Map<Object, Object>>();
        buildStack(stack, a);
        father.put(a.getKey(), stack.pop());
        return father;
    }

    private static void buildStack(Stack<Map<Object, Object>> stack, Struct struct) {
        Map<Object, Object> sun = new HashMap<>();
        Map<Object, Object> father = new HashMap<>();
        Map<Object, Object> unionMap = new HashMap<>();
        if (CollectionUtils.isEmpty(struct.getChildren())) {
            sun.put(struct.getKey(), struct.getValue());
            stack.push(sun);
        } else {
            for (Struct child : struct.getChildren()) {
                buildStack(stack, child);
                father.put(child.getKey(), stack.pop());
            }
            unionMap.put(struct.getKey(), father);
            stack.push(unionMap);
        }

        if (MapUtils.isNotEmpty(father)) {
            stack.push(father);
        }
    }

    private static Struct buildStruct(String key) {
        Struct result = new Struct();
        result.setKey(key);
        result.setValue("name是" + key);
        return result;
    }

}
