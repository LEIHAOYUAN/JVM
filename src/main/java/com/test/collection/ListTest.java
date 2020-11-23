package com.test.collection;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2020/11/23 15:27
 */
public class ListTest {

    public static void main(String[] args) {
        List<Long> ids = Lists.newArrayList();
        ids.add(null);
        ids.add(null);
        ids.add(null);
        ids.add(null);
        System.out.println(ids.size());
    }
}
