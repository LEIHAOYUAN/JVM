package com.stu.bigdecimal;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2020/5/21 15:51
 */
@Slf4j
public class BigDecimalTest {

    public static void main(String[] args) {
       log.info("是否为整数：{}",isIntegerValue(BigDecimal.valueOf(1.00000002500)));
    }

    private static void upAndDownTest() {
        BigDecimal aa = BigDecimal.TEN;
        BigDecimal bb = BigDecimal.valueOf(3);

        log.info("向上取整：{}", aa.divide(bb, 0, BigDecimal.ROUND_UP));
        log.info("向下取整：{}", aa.divide(bb, 0, BigDecimal.ROUND_DOWN));

        log.info("向上取整：{}", BigDecimal.valueOf(0.18).setScale(0, BigDecimal.ROUND_UP));
        log.info("向下取整：{}", BigDecimal.valueOf(0.58).setScale(0, BigDecimal.ROUND_DOWN));

        log.info("null比较：{}",BigDecimal.ZERO.compareTo(null));
    }

    private static boolean isIntegerValue(BigDecimal bd) {
        return bd.stripTrailingZeros().scale() <= 0;
    }

    /**
     * 测试 double类型转换为Bigdecimal
     * 结论：Double类型的数据转换为Bigdecimal，只有使用String类型才不会丢失数据
     */
    private static void testDouble() {
        // 字符串参数不会丢失精度
        BigDecimal param1 = new BigDecimal("1.000005268800000006366950002");
        System.out.println("param1:");
        System.out.println(param1.toPlainString());

        // 该情况下，Double已经丢失了精度
        Double paramD = 1.000005268800000006366950002D;
        BigDecimal param_1 = new BigDecimal(String.valueOf(paramD));
        System.out.println("param_1:");
        System.out.println(param_1.toPlainString());

        // Double参数会丢失精度
        BigDecimal param2 = BigDecimal.valueOf(1.000005268800000006366950002);
        System.out.println("param2:");
        System.out.println(param2.toPlainString());

        // Double参数会丢失精度
        BigDecimal param3 = new BigDecimal(1.000005268800000006366950002);
        System.out.println("param3:");
        System.out.println(param3.toPlainString());

    }

    private void testNull() {
        System.out.println(BigDecimal.ZERO.compareTo(null));
    }

    private void testCondition() {
        int aa = 100;
        if (aa > 10) {
            System.out.println("第一次执行");
        } else if (aa > 30) {
            System.out.println("第二次执行");
        }
    }

    private void testDetail() {
        System.out.println(BigDecimal.ZERO.compareTo(BigDecimal.TEN.negate()));
        System.out.println(BigDecimal.ZERO.negate().compareTo(BigDecimal.ZERO));
    }

    private void testNullPoint() {
        BigDecimal b = null;
        System.out.println(BigDecimal.ZERO.compareTo(b) == 0);
    }

    private void test() {
        BigDecimal decimal1 = new BigDecimal("0");
        BigDecimal decimal2 = new BigDecimal(2);
        decimal1.add(decimal2);
        System.out.println(decimal1.add(decimal1));
        System.out.println(decimal1.add(decimal2));

        System.out.println("校验BigDecimal是否为整数");
        System.out.println(testIsInteger(BigDecimal.valueOf(1.0004)));
    }


    public static boolean testIsInteger(BigDecimal param) {
        return param.remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO) == 0;
    }

}
