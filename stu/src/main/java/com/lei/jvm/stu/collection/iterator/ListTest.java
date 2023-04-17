package com.lei.jvm.stu.collection.iterator;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lei.jvm.stu.stream.Student;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2020/12/27 14:15
 */
@Slf4j
public class ListTest {

    public static void main(String[] args) {
        testSplitJoin();
    }

    private static void testGroup() {
        List<String> prefixes = Lists.newArrayList("AA", "B", "CCC");
        Student s1 = new Student();
        s1.setName("AAA111");
        Student s2 = new Student();
        s2.setName("AAA222");
        Student s3 = new Student();
        s3.setName("BBB");
        List<Student> param = Lists.newArrayList(s1, s2, s3);
        Map<String, List<Student>> groupMap = Maps.newHashMap();
        for (String prefix : prefixes) {
            if (CollectionUtils.isEmpty(groupMap.get(prefix))) {
                groupMap.put(prefix, Lists.newArrayList());
            }
            groupMap.get(prefix).addAll(param.stream().filter(i -> i.getName().contains(prefix)).collect(Collectors.toList()));
        }
        log.info("分组结果={}", JSON.toJSONString(groupMap));
    }

    private static void testSplitJoin() {
        List<String> param = Lists.newArrayList("AAA", "BBB", "CCC");
        param.addAll(Lists.newArrayList());
        log.info("拼接字符串结果：{}", StringUtils.join(param, "|"));
    }


    private static void testOrElse() {
        List<String> param = Lists.newArrayList();
        param.add("AAA");
        param.add("BBB");
        log.info("获取第一个元素：{}", JSON.toJSONString(param.stream().findFirst().orElse(null)));
    }

    private static void testGet() {
        List<String> param = Lists.newArrayList();
        param.add("AAA");
        param.add("BBB");
        log.info("获取第一个元素：{}", JSON.toJSONString(param.stream().findFirst().get()));
    }

    private static void testGetEmptyList() {
        List<Long> param = Lists.newArrayList();
        log.info("获取第一条数据：{}", param.get(0));
    }

    private static void test() {
        Student s1 = new Student();
        s1.setName("AAA");

        Student s2 = new Student();
        s2.setName("AAA");

        Student s3 = new Student();
        s3.setName("BBB");

        List<Student> list = Lists.newArrayList();
        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.removeIf(item -> item.getName().equals("AAA"));

        System.out.println(JSON.toJSONString(list));
    }

    private static void test01() {
        Student s1 = new Student();
        s1.setName("AAA");

        Student s2 = new Student();
        s2.setName("AAA");

        Student s3 = new Student();
        s3.setName("BBB");

        List<Student> list = Lists.newArrayList();
        list.add(s1);
        list.add(s2);
        list.add(s3);
        Iterator<Student> iterator = list.iterator();
        while (iterator.hasNext()) {
            Student item = iterator.next();
            if (item.getName().equals("AAA")) {
                iterator.remove();
                System.out.println("已经移除的：" + item.toString());
                System.out.println("集合大小：" + list.size());
                continue;
            }
            System.out.println("循环结束");
        }

        System.out.println(JSON.toJSONString(list));
    }
}
