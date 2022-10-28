package com.lei.jvm.spring;

import com.alibaba.fastjson.JSON;
import com.lei.jvm.spring.config.LocalLangPropertiesResourceConfig;
import com.lei.jvm.spring.config.LocalLangYmlResourceConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *  职能描述：加载本地资源文件测试
 *  @author leihaoyuan
 *  @version 2022/10/28 10:15
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class LocalLangResourceTest {

    @Autowired
    private LocalLangYmlResourceConfig localLangYmlResourceConfig;

    @Autowired
    private LocalLangPropertiesResourceConfig localLangPropertiesResourceConfig;

    @Test
    public void testLocalYmlResource() {
        log.info("读取到本地YML资源={}", JSON.toJSONString(localLangYmlResourceConfig.getLocalResourceMap()));
    }

    @Test
    public void testLocalPropertyResource() {
        log.info("读取到本地PROPERTY资源={}", JSON.toJSONString(localLangPropertiesResourceConfig.getLocalPropertyMap()));
    }


}
