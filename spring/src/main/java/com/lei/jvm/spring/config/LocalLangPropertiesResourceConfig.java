package com.lei.jvm.spring.config;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 *  职能描述：解析properties文件
 *  @author leihaoyuan
 *  @version 2022/10/28 14:12
 */
@Slf4j
@Data
@Component
public class LocalLangPropertiesResourceConfig {

    private Map<String, String> localPropertyMap = new ConcurrentHashMap<>();


    @PostConstruct
    public void loadLocalLangPropertiesResource() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("lang-back/message-back.properties");
        Properties properties = new Properties();
        properties.load(classPathResource.getInputStream());
        properties.forEach((key, value) -> {
            log.info("解析结果：{}，{}", key, value);
            if (null != key && null != value) {
                String key1 = (String) key;
                String value1 = (String) value;
                localPropertyMap.put(new String(key1.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8), new String(value1.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
            }
        });
    }


}
