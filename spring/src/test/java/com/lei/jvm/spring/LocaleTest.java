package com.lei.jvm.spring;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import java.util.Locale;

/**
 * 职能描述：国际化使用
 *
 * @author leihaoyuan
 * @version 2022/11/4 10:23
 * https://www.cnblogs.com/yangxiaohui227/p/11180640.html
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class LocaleTest {

    @Test
    public void testLocale() {
        log.info("Locale.getDefault().toString()={}", Locale.getDefault().toString());
        log.info("Locale.getDefault().toLanguageTag()={}", Locale.getDefault().toLanguageTag());
        log.info("Locale.CHINA={}", Locale.CHINA);
        // String toLanguageTag(Locale locale):把Locale转化成HTTP中Accept-Language能接受的本地化标准；比如标准的本地化字符串为：zh_CN，更改为zh-CN

        // parseLocaleString从本地化字符串中解析出本地化信息，相当于Locale.toString()的逆向方法
        Locale locale1 = StringUtils.parseLocaleString("zh-cn");
        Locale locale2 = StringUtils.parseLocaleString("zh-CN");
        Locale locale3 = StringUtils.parseLocaleString("zh_CN");

        log.info("Locale.CHINA={}", Locale.CHINA.toLanguageTag());
        log.info("Locale.SIMPLIFIED_CHINESE={}", Locale.SIMPLIFIED_CHINESE);
        log.info("Locale.TRADITIONAL_CHINESE={}", Locale.TRADITIONAL_CHINESE);

        log.info("zh-cn={}", locale1);
        log.info("zh-CN={}", locale2);
        log.info("zh_CN={}", locale3);
        log.info("local1==local2：{}", locale1 == locale2);
        log.info("local1==local3：{}", locale1 == locale3);
        log.info("local2==local3：{}", locale2 == locale3);
        log.info("local1==china：{}", locale1 == Locale.SIMPLIFIED_CHINESE);
        log.info("local2==china：{}", locale2 == Locale.SIMPLIFIED_CHINESE);
        log.info("local3==china：{}", locale3 == Locale.SIMPLIFIED_CHINESE);
    }


}
