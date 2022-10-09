package com.lei.jvm.stu.apache;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;

/**
 *  职能描述：Apache-pair方法测试
 *  @author leihaoyuan
 *  @version 2022/8/4 17:38
 */
@Slf4j
public class PairTest {

    public static void main(String[] args) {
        Pair<String,Integer> pair = Pair.of("zhangsan",12);
        String s = pair.getKey();
        int i = pair.getValue();
        System.out.println(s+","+i);
        String l = pair.getLeft();
        int r = pair.getRight();
        System.out.println(l+","+r);
    }



}
