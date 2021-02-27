package com.stu.mapstruct;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author leihaoyuan
 * @Date 2021/2/27 13:24
 * @Version 1.0
 * @Description
 */
@Slf4j
public class Test {

    public static void main(String[] args) {
        Person person = new Person(1L, "zhige", "zhige.me@gmail.com", new Date(), new User(1));
        PersonDTO personDTO = PersonConverter.INSTANCE.domain2dto(person);
        log.info("单个对象转换：Person：{}，personDTO：{}", JSON.toJSONString(person), JSON.toJSONString(personDTO));
        // 集合转换
        List<Person> listPerson = new ArrayList<>();
        listPerson.add(person);
        List<PersonDTO> listPersonDTO = PersonConverter.INSTANCE.domain2dto(listPerson);
        log.info("集合对象转换：listPerson：{}，listPersonDTO：{}", JSON.toJSONString(person), JSON.toJSONString(personDTO));
    }

}
