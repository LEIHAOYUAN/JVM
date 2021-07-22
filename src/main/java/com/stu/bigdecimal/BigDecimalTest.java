package com.stu.bigdecimal;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2020/5/21 15:51
 */
@Slf4j
public class BigDecimalTest {

    public static void main(String[] args) {
        log.info("校验是否为整数：{}", isIntegerValue(BigDecimal.valueOf(2.000000)));

        BigDecimal decimal = BigDecimal.valueOf(1.000958);
        log.info("测试向下取整：{}", decimal.setScale(0, RoundingMode.DOWN));
        testScale();

        BigDecimal aa = BigDecimal.TEN.setScale(0, BigDecimal.ROUND_UP);
        log.info("测试向上取整：{}", aa.toPlainString());
    }



    /**
     * 校验小数位数
     */
    private static void testScale(){
        BigDecimal decimal = BigDecimal.valueOf(188.095800);
        String s = decimal.stripTrailingZeros().toPlainString();
        int index = s.indexOf(".");
        log.info("有效小数位数：{}",index < 0 ? 0 : s.length() - index - 1);
    }




    public static void testOutOfScale() {
        BigDecimal aa = new BigDecimal("4.2558855678678978697464564564564564561561561561561564864156456456456487897897897945642346478978978948648646848674864948645645645645645645645645645645645645645645645645645645645645");
        log.info("结果：{}", aa.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());
    }


    public static void testUp() {
        BigDecimal roundDown = BigDecimal.valueOf(0.999990).divide(BigDecimal.ONE, 0, BigDecimal.ROUND_DOWN);
        BigDecimal result = roundDown.multiply(BigDecimal.valueOf(1.258));
        log.info("零乘法：结果：{}，与零比较结果：{}", result, BigDecimal.ZERO.compareTo(result) == 0);

        log.info("向下取整：{}", roundDown);

        log.info("去除多余零：{}", BigDecimal.valueOf(0.562330000000D).toPlainString());

        log.info("是否为整数：{}", isIntegerValue(BigDecimal.valueOf(1.00000002500)));

        log.info("零值比较结果：{}", BigDecimal.ZERO.compareTo(null));
    }

    private static void upAndDownTest() {
        BigDecimal aa = BigDecimal.TEN;
        BigDecimal bb = BigDecimal.valueOf(3);

        log.info("向上取整：{}", aa.divide(bb, 0, BigDecimal.ROUND_UP));
        log.info("向下取整：{}", aa.divide(bb, 0, BigDecimal.ROUND_DOWN));

        log.info("向上取整：{}", BigDecimal.valueOf(0.18).setScale(0, BigDecimal.ROUND_UP));
        log.info("向下取整：{}", BigDecimal.valueOf(0.58).setScale(0, BigDecimal.ROUND_DOWN));

        log.info("null比较：{}", BigDecimal.ZERO.compareTo(null));
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
