package com.lei.jvm.stu.collection.map.pojo;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

@Slf4j
public class ParseStruct {


    public static void main(String[] args) {
        parseStruct();
    }

    private static void parseStruct() {
        Struct a = buildStruct("a", StructTypeEnum.OBJECT);
        Struct a1 = buildStruct("a1", StructTypeEnum.OBJECT);
        Struct a2 = buildStruct("a2", StructTypeEnum.ARRAY);

        Struct a11 = buildStruct("a11", StructTypeEnum.ARRAY);
        Struct a12 = buildStruct("a12", StructTypeEnum.BASE);

        Struct a21 = buildStruct("a21", StructTypeEnum.OBJECT);

        Struct a111 = buildStruct("a111",StructTypeEnum.OBJECT);
        Struct a112 = buildStruct("a112",StructTypeEnum.BASE);

        Struct a211 = buildStruct("a211", StructTypeEnum.BASE);

        Struct a1111 = buildStruct("a1111",StructTypeEnum.BASE);
        Struct a1112 = buildStruct("a1112",StructTypeEnum.BASE);

        a.setChildren(Lists.newArrayList(a1, a2));
        // a1节点
        a1.setChildren(Lists.newArrayList(a11, a12));
        a11.setChildren(Lists.newArrayList(a111, a112));
        a111.setChildren(Lists.newArrayList(a1111,a1112));
        // a2节点
        a2.setChildren(Lists.newArrayList(a21));
        a21.setChildren(Lists.newArrayList(a211));


        log.info("嵌套对象结构={}", JSON.toJSONString(a));

        Map<String, Object> objectObjectMap = buildStructMap(a);
        log.info("嵌套层级解析结果={}", JSON.toJSONString(objectObjectMap));
    }

    private static Map<String, Object> buildStructMap(Struct struct) {
        if (null == struct || StructTypeEnum.OBJECT != struct.getStructType()) {
            throw new IllegalArgumentException("非法结构体");
        }
        Stack<Object> stack = new Stack<>();
        buildStack(stack, struct);
        return (Map<String, Object>) stack.pop();
    }

    private static void buildStack(Stack<Object> stack, Struct struct) {
        Map<String, Object> father = new HashMap<>();
        Map<String, Object> unionMap = new HashMap<>();
        if (StructTypeEnum.BASE == struct.getStructType()) {
            stack.push(struct.getValue());
        } else if (StructTypeEnum.ARRAY == struct.getStructType()) {
            List<Struct> children = struct.getChildren();
            List<Object> list = new ArrayList<>(10);
            for (Struct child : children) {
                buildStack(stack, child);
                list.add(stack.pop());
            }
            unionMap.put(struct.getKey(), list);
            stack.push(unionMap);
        } else if (StructTypeEnum.OBJECT == struct.getStructType()) {
            Map<String, Object> map = new HashMap<>();
            for (Struct child : struct.getChildren()) {
                buildStack(stack, child);
                map.put(child.getKey(), stack.pop());
            }
            father.put(struct.getKey(), map);
        }

        if (MapUtils.isNotEmpty(father)) {
            stack.push(father);
        }
    }

    private static Struct buildStruct(String key, StructTypeEnum type) {
        Struct struct = new Struct();
        struct.setKey(key);
        struct.setValue("name是" + key);
        struct.setStructType(type);
        return struct;
    }

}
