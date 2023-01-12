package com.lei.jvm.spring;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.lei.jvm.json.Person;
import com.lei.jvm.json.Student;
import com.lei.jvm.json.Teacher;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

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
                "                    \"title\": \"穿衣\",\n" +
                "                    \"des\": \"建议着厚外套加毛衣等服装\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"title\": \"紫外线强度\",\n" +
                "                    \"des\": \"属中等强度紫外线辐射天气\"\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        Object read = JSONPath.read(json, "$.results.index.des");
        log.info("结果={}",JSON.toJSONString(read));

    }



}
