package com.lei.jvm.spring.config;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lei.jvm.spring.model.LocalLangResourceModel;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *  职能描述：读取本地配置文件
 *  @author leihaoyuan
 *  @version 2022/10/28 9:56
 */
@Slf4j
@Data
@Component
public class LocalLangResourceConfig {

    private Map<String, LocalLangResourceModel> localResourceMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void loadLocalLangResource() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("lang/resource.yml");
        Yaml yaml = new Yaml();
        Map map = yaml.loadAs(classPathResource.getInputStream(), Map.class);
        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(map));
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            String key = entry.getKey();
            String s = JSON.toJSONString(entry.getValue());
            LocalLangResourceModel item = JSON.parseObject(s, LocalLangResourceModel.class);
            if (null != item && StringUtils.isNotBlank(item.getValue())) {

                localResourceMap.put(key, item);
            }

        }
        log.info(JSON.toJSONString(localResourceMap));
    }


}
