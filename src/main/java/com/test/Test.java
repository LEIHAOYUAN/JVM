package com.test;

import org.springframework.util.StringUtils;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2019/11/25 14:56
 */
public class Test {

    public static void main(String[] args) {


        Long count = 0L;
        if (count == null) {
            System.out.println("===");
            return;
        }
        if (count <= 0) {
            System.out.println("---");
            return;
        }
        System.out.println("***");
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
