package com.test;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2019/11/25 14:56
 */
public class Test {

    public static void main(String[] args) {
        ParentPerson parentPerson = new ParentPerson();
        parentPerson.setAge(100);
        Person person = new Person();
        System.out.println(person.getAge());

    }

    private static void testList() {
        List<Person> personList = Lists.newArrayList();
        Person pp = new Person();
        for (int i = 0; i < 5; i++) {
            pp = new Person();
            pp.setName("A" + i);
            personList.add(pp);
        }
        System.out.println(personList.size());
        System.out.println(JSON.toJSONString(personList));

        System.out.println("--------------------------------------");
    }

    private static boolean validateRelationStockNo(String relationStockNo) {
        if (StringUtils.isEmpty(relationStockNo)) {
            return true;
        }
        if (relationStockNo.startsWith("TRS") || relationStockNo.startsWith("ZZTRS")) {
            return true;
        }
        return false;
    }


}
