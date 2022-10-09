package com.lei.jvm.stu.optional;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2020/10/9 15:47
 */
@Slf4j
public class OptionalTeset {


    public static void main(String[] args) {
        testNullable();
    }


    private static void testList() {
//        List<Person> list = Lists.newArrayList();
        List<Person> list = null;
//        list.add(new Person("zhangdan",90));
//        list.add(new Person("lsihi",90));

//        System.out.println(list.stream().findFirst());
        Optional.ofNullable(list).orElseThrow(() -> new RuntimeException());
    }


    private static void testNullable() {
        Person person = new Person("zhangdan", 90);
        Optional<Person> opt = Optional.ofNullable(person);
        log.info("获取对象：{}", JSON.toJSONString(opt.get()));

        Optional<Person> opt2 = Optional.ofNullable(null);
        log.info("获取对象2：{}", JSON.toJSONString(opt2.orElse(null)));
    }


}
