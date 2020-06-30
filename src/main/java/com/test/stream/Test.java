package com.test.stream;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2020/5/28 22:36
 */
public class Test {

    public static void main(String[] args) {
        List<String> regionList = Lists.newArrayList();
        List<String> collect = regionList.stream().filter(item -> "99".equals(item)).collect(Collectors.toList());
        System.out.println(collect.size());

        List<String> collect1 = collect.stream().filter(item -> "111".equals(item)).collect(Collectors.toList());
        System.out.println(collect1.size());

        System.out.println("MAIN------start");
        testException();
        System.out.println("MAIN------end");

    }

    private static void test() {
        List<Integer> list = Lists.newArrayList();
        list.add(3);
        list.add(4);
        list.add(3);
        list.add(4);
        list.add(0);
        list.add(30);

        List<Integer> i = list.stream()
                .filter(item -> item > 100)
                .sorted(Comparator.comparing(integer -> integer.intValue()))
                .limit(1).collect(Collectors.toList());
        System.out.println(i);
        BigDecimal a = BigDecimal.ONE;
        BigDecimal b = BigDecimal.TEN;
        System.out.println(b.subtract(a).divide(BigDecimal.TEN));
    }


    private static void testException() {
        System.out.println("METHOD-------start");
//        throw new RuntimeException("异常");
        System.out.println(1 / 0);
        System.out.println("METHOD---------end");
    }
}
