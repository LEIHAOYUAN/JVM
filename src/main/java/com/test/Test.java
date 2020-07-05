package com.test;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2019/11/25 14:56
 */
public class Test {

    public static void main(String[] args) {

        Set<String> aa = Sets. newHashSet();
        aa.add("aa");
        aa.add("aa");
        System.out.println(aa);
//        testList();

//        String test = "3,4";
//        System.out.println(test.contains("4"));
//        BigDecimal buyNum = BigDecimal.valueOf(10000).divide(BigDecimal.valueOf(100),10, BigDecimal.ROUND_HALF_UP).divide(BigDecimal.valueOf(3),10, BigDecimal.ROUND_HALF_UP).setScale(3, BigDecimal.ROUND_HALF_UP);
//        System.out.println(buyNum);
        System.out.println(String.format("%04d", new Random().nextInt(10000)));
//        System.out.println(System.currentTimeMillis());
//        System.out.println(new Random().nextInt(9999));
//        System.out.println(""+1+3);
        for (int i = 0; i < 200; i++) {
            System.out.println(""+System.nanoTime()+String.format("%04d", new Random().nextInt(10000)));
        }

    }

    private static void testList() {
        List<Person> personList = Lists.newArrayList();
        Person pp;
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
