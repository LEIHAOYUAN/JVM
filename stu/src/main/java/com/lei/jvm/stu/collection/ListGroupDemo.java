package com.lei.jvm.stu.collection;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author leihaoyuan
 * @Date 2021/12/16 16:15
 * @Version 1.0
 * @Description
 */
@Slf4j
public class ListGroupDemo {

    public static void main(String[] args) {
        test();
    }

    /*
    这个表格里面有282人，你把他们分成6组，每组47人，然后47人再随机分成10，10，9，9，9;
    要求是同个部门id的人尽量分布在30个组里面，比如部门id为12的有33人，那么每组1人，有3个组是每组2人
     */
    public static void test() {
        List<Person> param = buildParam();
        // 分组数量
        int groupNum = 4;
        // 每组人数(作为集合偏移量)
        int groupSize = BigDecimal.valueOf(param.size()).divide(BigDecimal.valueOf(groupNum), 0, RoundingMode.UP).intValue();
        log.info("总数：{}，分组个数：{}，分组大小：{}", param.size(), groupNum, groupSize);

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
        log.info("最终分组结果：{}", JSON.toJSONString(resList));
    }

    private static List<Person> buildParam() {
        List<Person> param = Lists.newArrayList();
        param.add(new Person("A1", 1));
        param.add(new Person("A2", 1));
        param.add(new Person("A3", 1));
        param.add(new Person("A4", 1));
        param.add(new Person("A5", 1));
        param.add(new Person("B1", 2));
        param.add(new Person("B2", 2));
        param.add(new Person("B3", 2));
        param.add(new Person("B4", 2));
        param.add(new Person("B5", 2));
        param.add(new Person("B6", 2));
        param.add(new Person("B7", 2));
        param.add(new Person("B8", 2));
        param.add(new Person("B9", 2));
        param.add(new Person("B10", 2));
        param.add(new Person("B11", 2));
        param.add(new Person("C1", 3));
        param.add(new Person("C2", 3));
        param.add(new Person("C3", 3));
        param.add(new Person("C4", 3));
        param.add(new Person("C5", 3));
        param.add(new Person("C6", 3));
        param.add(new Person("C7", 3));
        param.add(new Person("C8", 3));
        param.add(new Person("C9", 3));
        param.add(new Person("C10", 3));
        param.add(new Person("D1", 4));
        param.add(new Person("D2", 4));
        param.add(new Person("D3", 4));
        param.add(new Person("D4", 4));
        return param;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Person {
        private String name;
        private Integer dept;
    }


}
