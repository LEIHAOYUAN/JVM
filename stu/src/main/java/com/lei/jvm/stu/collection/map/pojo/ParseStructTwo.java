package com.lei.jvm.stu.collection.map.pojo;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

@Slf4j
public class ParseStructTwo {


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

        Struct a111 = buildStruct("a111", StructTypeEnum.OBJECT);
        Struct a112 = buildStruct("a112", StructTypeEnum.BASE);

        Struct a211 = buildStruct("a211", StructTypeEnum.BASE);

        Struct a1111 = buildStruct("a1111", StructTypeEnum.BASE);
        Struct a1112 = buildStruct("a1112", StructTypeEnum.BASE);

        a.setChildren(Lists.newArrayList(a1, a2));
        // a1节点
        a1.setChildren(Lists.newArrayList(a11, a12));
        a11.setChildren(Lists.newArrayList(a111, a112));
        a111.setChildren(Lists.newArrayList(a1111, a1112));
        // a2节点
        a2.setChildren(Lists.newArrayList(a21));
        a21.setChildren(Lists.newArrayList(a211));


        log.info("嵌套对象结构={}", JSON.toJSONString(a));
        log.info("嵌套层级解析结果={}", JSON.toJSONString(buildStructMap(a)));
    }

    private static Map<String, Object> buildStructMap(Struct struct) {
        if (null == struct || StructTypeEnum.OBJECT != struct.getStructType()) {
            throw new IllegalArgumentException("非法结构体");
        }
        Map<String, Object> result = Maps.newHashMap();
        Stack<Object> stack = new Stack<>();
        buildStack(stack, struct);
        result.put(struct.getKey(), stack.pop());
        return result;
    }

    private static void buildStack(Stack<Object> stack, Struct struct) {
//        Map<String, Object> father = new HashMap<>();
//        Map<String, Object> unionMap = new HashMap<>();
        if (null == struct) {
            return;
        }
        if (StructTypeEnum.BASE == struct.getStructType()) {
//            Map<String, Object> map = new HashMap<>();
//            map.put(struct.getKey(), struct.getValue());
//            stack.push(map);
            stack.push(struct.getValue());
        } else if (StructTypeEnum.ARRAY == struct.getStructType()) {
            List<Struct> children = struct.getChildren();
            List<Object> list = Lists.newArrayList();
            for (Struct child : children) {
                if (StructTypeEnum.BASE == child.getStructType()) {
                    Map<String, Object> map = new HashMap<>();
                    map.put(struct.getKey(), struct.getValue());
                    list.add(map);
                    continue;
                }
                buildStack(stack, child);
                list.add(stack.pop());
            }
//            unionMap.put(struct.getKey(), list);
            stack.push(list);
        } else if (StructTypeEnum.OBJECT == struct.getStructType()) {
            Map<String, Object> map = new HashMap<>();
            for (Struct child : struct.getChildren()) {
                if (StructTypeEnum.BASE == child.getStructType()) {
                    Map<String, Object> sun = new HashMap<>();
                    sun.put(child.getKey(), struct.getValue());
                    map.put(struct.getKey(), sun);
                    continue;
                }
                buildStack(stack, child);
                map.put(child.getKey(), stack.pop());
            }
            stack.push(map);
//            father.put(struct.getKey(), map);
        }

//        if (MapUtils.isNotEmpty(father)) {
//            stack.push(father);
//        }
    }

    private static Struct buildStruct(String key, StructTypeEnum type) {
        Struct struct = new Struct();
        struct.setKey(key);
        struct.setValue("name是" + key);
        struct.setStructType(type);
        return struct;
    }

}

