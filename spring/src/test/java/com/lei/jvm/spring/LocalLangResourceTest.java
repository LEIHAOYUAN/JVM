package com.lei.jvm.spring;

import com.alibaba.fastjson.JSON;
import com.lei.jvm.spring.config.LocalLangPropertiesResourceConfig;
import com.lei.jvm.spring.config.LocalLangYmlResourceConfig;
import com.lei.jvm.spring.model.LocalLangResourceModel;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *  职能描述：加载本地资源文件测试
 *  @author leihaoyuan
 *  @version 2022/10/28 10:15
 *  json<——>yml在线转换
 *  https://www.toolscat.com/json/json-yml
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

    @Test
    public void testTransferLangResource() {
        Map<String, String> localPropertyMap = localLangPropertiesResourceConfig.getLocalPropertyMap();
        Map<String, LocalLangResourceModel> ymlResourceMap = new ConcurrentHashMap<>();
        LocalLangResourceModel model;
        for (Map.Entry<String, String> entry : localPropertyMap.entrySet()) {
            model = new LocalLangResourceModel();
            model.setValue(entry.getValue());
            model.setVersion(0);
            ymlResourceMap.put(entry.getKey(), model);
        }
        log.info("转换结果={}", JSON.toJSONString(ymlResourceMap));
    }


}
