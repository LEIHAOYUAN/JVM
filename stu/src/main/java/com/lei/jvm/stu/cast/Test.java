package com.lei.jvm.stu.cast;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author leihaoyuan
 * @Date 2021/2/7 16:40
 * @Version 1.0
 * @Description
 */
@Slf4j
public class Test {

    public static void main(String[] args) {
        Son son = new Son("AAA");
        String jsonSon = JSON.toJSONString(son);

        String s = (String) JSON.parseObject(jsonSon, Object.class);


    }

}

enum Type {
    SIMPLE(String.class);
    @Getter
    Class clazz;

    Type(Class clazz) {
        this.clazz = clazz;
    }
}

@Data
class Father {

}

@Data
@AllArgsConstructor
class Son extends Father {
    private String name;
}


