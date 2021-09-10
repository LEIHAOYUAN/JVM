package com.lei.stu.string;

import com.base.utils.valid.ValidUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description https://mp.weixin.qq.com/s/2UoEhKbK02fkR-aBGp3c1Q
 * @Author leihaoyuan
 * @Date 2020/4/17 10:16
 */
@Slf4j
public class Test {

    public static void main(String[] args) {
        testTrim();
    }


    private static void testTrim() {
        String param = "  2  ";
        String param1 = "             ";
        log.info("trim转换结果：{}", Long.valueOf(param.trim()));
        log.info("trim转换结果：{}", param1.trim().length());
    }

    private static void testSubString() {
        String param = "2021050600";
        log.info(param.substring(0, param.length() - 2));
    }


    public static void convertCodePath() {
//        String codePath = "20500000019,20500000020,20500000021";
//        String codePath = "    20500000019,20500000020,20500000021 ";
        String codePath = "";
        String[] split = codePath.split(",");
        log.info("切割后长度：{}", split.length);
    }

    public static String buildCompareDiffOrderNo(String compareDate, Long cellCode, Integer stockStatus) {
        ValidUtil.notBlank(compareDate, "【每日库存校对单号】校对时间不能为空");
        ValidUtil.notNull(cellCode, "【每日库存校对单号】，库存单位编号不能为空");
        ValidUtil.notNull(stockStatus, "【每日库存校对单号】，库存状态不能为空");
        int cellLen = cellCode.toString().length();
        return "VHL".concat(compareDate.substring(2, 8)).concat(cellCode.toString().substring(cellLen - 8, cellLen)).concat(stockStatus.toString());
    }


    private void test() {
        System.out.println(1 + 2 + "hello");
        System.out.println("" + 1 + 2 + "hello");
    }

    private static void test01() {
        String str1 = new StringBuilder("深入理解").append("虚拟机").toString();
        System.out.println(str1.intern() == str1);
        //
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);
    }

    /**
     * 测试字符串拼接concat
     */
    private static void test02() {
        System.out.println("TEST".concat("     "));
        System.out.println("TEST".concat(null));
    }
}
