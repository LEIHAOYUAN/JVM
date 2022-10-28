package com.lei.jvm.spring.config;


import com.lei.jvm.spring.model.LocalLangResourceModel;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 *  职能描述：读取本地配置文件
 *  @author leihaoyuan
 *  @version 2022/10/28 9:56
 */
@SuppressWarnings("ConfigurationProperties")
@Data
@Component
@PropertySource(factory = YamlPropertyLoaderFactory.class, value = "classpath:lang/resource.yml")
@ConfigurationProperties
public class LocalLangResourceConfig {

    private Map<String, LocalLangResourceModel> localResourceMap;

}
