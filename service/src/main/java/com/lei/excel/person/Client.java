package com.lei.excel.person;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author leihaoyuan
 * @Date 2021/12/16 16:22
 * @Version 1.0
 * @Description
 */
@Slf4j
public class Client {

    public static void main(String[] args) {
        test(new PersonService().getData());
    }

    /**
     * 这个表格里面有282人，你把他们分成6组，每组47人，然后47人再随机分成10，10，9，9，9;
     * 要求是同个部门id的人尽量分布在30个组里面，比如部门id为12的有33人，那么每组1人，有3个组是每组2人
     */
    public static void test(List<Person> param) {
        // 分组数量
        int groupNum = 30;
        // 初始化最终分组集合
        List<List<Person>> resList = Lists.newArrayList();
        for (int i = 0; i < groupNum; i++) {
            resList.add(Lists.newArrayList());
        }

        // 按照部门分组
        for (Map.Entry<Integer, List<Person>> entry : param.stream().collect(Collectors.groupingBy(Person::getDept, Collectors.toList())).entrySet()) {
            for (Person person : entry.getValue()) {
                // 获取最小集合添加元素
                resList.stream().min(Comparator.comparingInt(List::size)).get().add(person);
            }
        }
        System.out.println(JSON.toJSONString(resList));
    }

}
