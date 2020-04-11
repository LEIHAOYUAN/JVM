package com.test;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2019/11/25 14:56
 */
public class Test {

    public static void main(String[] args) {
//        testList();

        String test = "3,4";
        System.out.println(test.contains("4"));
        BigDecimal buyNum = BigDecimal.valueOf(10000).divide(BigDecimal.valueOf(100),10, BigDecimal.ROUND_HALF_UP).divide(BigDecimal.valueOf(3),10, BigDecimal.ROUND_HALF_UP).setScale(3, BigDecimal.ROUND_HALF_UP);
        System.out.println(buyNum);
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
