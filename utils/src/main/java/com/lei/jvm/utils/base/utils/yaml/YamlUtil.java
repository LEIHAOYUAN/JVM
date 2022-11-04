package com.lei.jvm.utils.base.utils.yaml;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author leihaoyuan
 * @Date 2022/4/18 10:59
 * @Version 1.0
 * @Description yaml转换工具类
 */
@Slf4j
public class YamlUtil {

    public static void main(String[] args) {
        transferFile("E:\\message.properties");
    }


    public static String transferFile(String propertiesFilePath) {
        InputStream inputStream = null;
        try {
            inputStream = Files.newInputStream(Paths.get(propertiesFilePath));
        } catch (Exception ex) {
            log.error("文件打开异常：{}", ex.getMessage(), ex);
            return "转换错误";
        }
        Map<String, LocalLangResourceModel> localResourceMap = new ConcurrentHashMap<>();
        Yaml yaml = new Yaml();
        Map map = yaml.loadAs(inputStream, Map.class);
        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(map));
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            String key = entry.getKey();
            String s = JSON.toJSONString(entry.getValue());
            LocalLangResourceModel item = JSON.parseObject(s, LocalLangResourceModel.class);
            if (null != item && org.apache.commons.lang3.StringUtils.isNotBlank(entry.getKey()) && StringUtils.isNotBlank(item.getValue())) {
                localResourceMap.put(key, item);
            }
        }
        return new Yaml().dumpAsMap(localResourceMap);
    }

    public static void generteYmlFile(LinkedHashMap<String, Map<String,Object>> ymlMap, String filePath) {
        try {
            DumperOptions dumperOptions = new DumperOptions();
            dumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
            dumperOptions.setCanonical(false);
            FileWriter fileWriter = new FileWriter(new File(filePath));
            //YAML类是API的入口点
//            YAMLMapper yamlMapper = new YAMLMapper();
            Yaml yaml = new Yaml(dumperOptions);
            yaml.dump(ymlMap, fileWriter);
        } catch (Exception ex) {
            log.error("生成文件异常={}", ex.getMessage(), ex);
        }

    }


    /**
     * 将yaml文件加载为标准json对象（加载后的对象类型是JSONObject不是LinkedHashMap）
     * @param filePath 文件路径
     * @return 标注json对象
     */
    private static JSONObject loadAsNormalJsonObject(String filePath) {
        Yaml yaml = new Yaml();
        Map jsonObject = yaml.loadAs(convertToString(filePath), Map.class);
        String jsonStr = JSONUtil.toJsonStr(jsonObject);
        JSONObject normalJsonObj = JSONObject.parseObject(jsonStr);
        return normalJsonObj;
    }

    public static String convertToString(String filePath) {
        String conf = null;
        InputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(filePath));
            conf = IOUtils.toString(in, String.valueOf(StandardCharsets.UTF_8));
            System.out.println(conf);
        } catch (IOException e) {
            log.error("文件读取失败", e);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                log.error("文件关闭失败", e);
            }
        }
        return conf;
    }


}
