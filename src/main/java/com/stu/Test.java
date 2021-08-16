package com.stu;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2019/11/25 14:56
 */
@Slf4j
public class Test {

    public static void main(String[] args) {
        BigDecimal bigDecimal = null;

        BigDecimal param = BigDecimal.ZERO;

        log.info("校对结果：{}",param.equals(bigDecimal));

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
