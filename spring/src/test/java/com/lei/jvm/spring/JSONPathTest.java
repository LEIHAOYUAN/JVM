package com.lei.jvm.spring;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPath;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

/**
 *  职能描述：
 *  @author leihaoyuan
 *  @version 2023/1/12 10:48
 */
@Slf4j
public class JSONPathTest {


    @Test
    public void testNestJSON() {
        String json = "{\n" +
                "    \"error\": 0,\n" +
                "    \"status\": \"success\",\n" +
                "    \"results\": [\n" +
                "        {\n" +
                "            \"currentCity\": \"青岛\",\n" +
                "            \"index\": [\n" +
                "                {\n" +
                "                    \"id\": 1,\n" +
                "                    \"title\": \"穿衣\",\n" +
                "                    \"des\": \"建议着厚外套加毛衣等服装\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 2,\n" +
                "                    \"title\": \"紫外线强度\",\n" +
                "                    \"des\": \"属中等强度紫外线辐射天气\"\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        Object array = JSONPath.read(json, "$..index.id");
        log.info("[array]结果={}", JSON.parseArray(JSON.toJSONString(array), Integer.class));

        Object obj = JSONPath.read(json, "$.error");
        log.info("[obj]结果={}", JSON.parseObject(JSON.toJSONString(obj), String.class));

    }

    private static List<Object> parseJSONArray(String json, String tag) {
        List<String> strings = JSON.parseArray(json, String.class);
        return null;
    }


}
