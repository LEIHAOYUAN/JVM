package com.lei.jvm.spring;

import com.alibaba.fastjson.JSON;
import com.lei.jvm.spring.config.LocalLangResourceConfig;
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
    private LocalLangResourceConfig localLangResourceConfig;

    @Test
    public void testLocalResource() {
        log.info("读取到本地资源={}", JSON.toJSONString(localLangResourceConfig.getLocalResourceMap()));
    }

}
