package com.lei.jvm.utils.base.utils.awt.ymlutil;

import com.lei.jvm.utils.base.utils.yaml.YamlUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

/**
 *  职能描述：
 *  @author leihaoyuan
 *  @version 2022/11/4 17:17
 */
@Slf4j
public class PropertiesUtil {


    public static String transfer(String filePath, String codedFormat) {
        String tips = null;
        if (StringUtils.isBlank(filePath)) {
            return "文件路径为空";
        }
        try {
            Map<String, String> localPropertyMap = new LinkedHashMap<>();
            Properties properties = new Properties();
            properties.load(Files.newInputStream(Paths.get(filePath)));
            properties.forEach((key, value) -> {
                log.info("解析结果：{}，{}", key, value);
                if (null != key && null != value) {
                    String key1 = (String) key;
                    String value1 = (String) value;
                    if (StandardCharsets.UTF_8.name().equals(codedFormat)) {
                        localPropertyMap.put(new String(key1.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8), new String(value1.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
                    } else if (StandardCharsets.US_ASCII.name().equals(codedFormat)) {
                        localPropertyMap.put(new String(key1.getBytes(StandardCharsets.US_ASCII), StandardCharsets.UTF_8), new String(value1.getBytes(StandardCharsets.US_ASCII), StandardCharsets.UTF_8));
                    } else if (StandardCharsets.ISO_8859_1.name().equals(codedFormat)) {
                        localPropertyMap.put(new String(key1.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8), new String(value1.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
                    } else {
                        localPropertyMap.put(key1, value1);
                    }
                }
            });
            LinkedHashMap<String, Map<String, Object>> ymlMap = new LinkedHashMap<>();
            for (Map.Entry<String, String> entry : localPropertyMap.entrySet()) {
                Map<String, Object> item = new HashMap<>();
                item.put("value", entry.getValue());
                item.put("version", 1);
                ymlMap.put(entry.getKey(), item);
            }
            String ymlFilePath = filePath.substring(0, filePath.lastIndexOf(".")).concat(".yml");
            YamlUtil.generteYmlFile(ymlMap, ymlFilePath);
            return "YML文件已生成：" + ymlFilePath;
        } catch (Exception ex) {
            tips = "文件转换异常";
        }
        return tips;
    }


}
