package com.base.utils.yaml;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @Author leihaoyuan
 * @Date 2022/4/18 10:59
 * @Version 1.0
 * @Description yaml转换工具类
 */
@Slf4j
public class YamlUtil {

    public static void main(String[] args) throws IOException {
        String source = "/Users/liyinlong/elasticsearch/values.yaml";
        JSONObject srcObject = loadAsNormalJsonObject(source);

        System.out.println(srcObject);
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
