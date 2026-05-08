package com.lei.jvm.stus.stu;

import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ryan
 */
@Slf4j
public class RegexTest {

    static void main() {
        testBOGO();
        testFreeItem();
    }

    private static void testBOGO() {
        String offerText = "Buy  one Cosby Burger, geT  One 1 Herring Filet Free";
        String regex = "(?i)buy\\s*one\\s+(.*?)\\s*,\\s*get\\s*one\\s+(.*)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(offerText);
        if (matcher.find()) {
            log.info("✅ 匹配成功！");
        } else {
            log.info("❌ 匹配失败");
        }
    }

    private static void testFreeItem() {
        String offerText = "   spend $10, Get One Coffee  FrEe  ";
        String regex = "(?i)^\\s*Spend.*Free\\s*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(offerText);
        if (matcher.find()) {
            log.info("✅ 匹配成功！");
        } else {
            log.info("❌ 匹配失败");
        }
    }
}
