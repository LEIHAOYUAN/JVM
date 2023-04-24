package com.lei.jvm.spring.cglib;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class Run {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static void main(String[] args) throws JsonProcessingException, InvocationTargetException, IllegalAccessException {

        User user = new User();
        user.setName("Daisy");
        System.out.println("User：" + MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(user));

        Map<String, Object> propertiesMap = new HashMap<>(1);
        propertiesMap.put("age", 18);

        Object obj = ReflectUtil.getObject(user, propertiesMap);
        System.err.println("动态为User添加age之后，User：" + MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(obj));
    }
}




