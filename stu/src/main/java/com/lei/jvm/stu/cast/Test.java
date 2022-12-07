package com.lei.jvm.stu.cast;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * @Author leihaoyuan
 * @Date 2021/2/7 16:40
 * @Version 1.0
 * @Description
 */
@Slf4j
public class Test {

    public static void main(String[] args) {

        log.info("转换结果={}", JSON.parseObject(null, Boolean.class));
        log.info("转换结果={}", JSON.parseObject("ppp", Integer.class));
        log.info("转换结果={}", JSON.parseObject("1.256", BigDecimal.class));
    }

    private static <T> T parseProp(Class<T> clazz) {
        Son son = new Son("AAA");
        Class<? extends String> resClass = son.getName().getClass();
        boolean equals = resClass.equals(clazz);
        log.info("比较结果={}", equals);
        if (equals) {
            return (T) son.getName();
        }
        return null;
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

interface IFather {

}

@Data
class Father implements IFather {

}

interface ISon {
    String getName();
}

@Data
@AllArgsConstructor
class Son extends Father implements ISon {
    private String name;
}


