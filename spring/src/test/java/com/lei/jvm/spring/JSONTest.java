package com.lei.jvm.spring;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lei.jvm.json.Person;
import com.lei.jvm.json.Student;
import com.lei.jvm.json.Teacher;
import com.lei.jvm.spring.dto.liteflow.LiteFlowChainModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  职能描述：
 *  @author leihaoyuan
 *  @version 2022/11/24 10:10
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class JSONTest {

    @Test
    public void testNestJSON() {
        Map<String, Object> maps = new HashMap<>();
        Person person = new Person();
        person.setName("人类");
        Teacher teacher = new Teacher();
        teacher.setName("教师");
        Student student = new Student();
        student.setName("学生");

        teacher.setStudents(Lists.newArrayList(student));
        person.setTeacher(teacher);

        Map map = JSON.parseObject(JSON.toJSONString(person), Map.class);

        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(map));
        log.info("转换结果={}", jsonObject.toJSONString());
    }

    @Test
    public void testNestMap() {
        Map<String, Object> maps = new HashMap<>();
        maps.put("name", "人类");
        maps.put("teacher.name", "教师");
        maps.put("teacher.students.name", "学生");

        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(maps));
        log.info("解析JSONobject");
    }

    /**
     {
     "query": "",
     "variables": {
     "filters": ["filter1","filter2"],
     "valids": [
     {
     "userId": "XXX"
     },
     {
     "orderNo": "XXX",
     "orderType": 1
     }
     ],
     "page": 1,
     "pageSize": 10
     },
     "extra": { "viewId": "63a11d5b73d9d425311bf1d6"}
     }
     */
    @Test
    public void testParseJson() {
        String param = "{\n" +
                "    \"query\": \"getList\",\n" +
                "    \"variables\": {\n" +
                "        \"filters\": [\n" +
                "            \"filter1\",\n" +
                "            \"filter2\"\n" +
                "        ],\n" +
                "        \"valids\": [\n" +
                "            {\n" +
                "                \"userId\": \"XXX\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"orderNo\": \"XXX\",\n" +
                "                \"orderType\": 1\n" +
                "            }\n" +
                "        ],\n" +
                "        \"page\": 1,\n" +
                "        \"pageSize\": 10\n" +
                "    },\n" +
                "    \"extra\": {\n" +
                "        \"viewId\": \"63a11d5b73d9d425311bf1d6\"\n" +
                "    }\n" +
                "}";

        String formatTag = "query.viewId";
        JSONObject jsonObject = JSON.parseObject(param);
        if (StringUtils.isBlank(formatTag)) {
            log.info("待解析参数为空");
            return;
        }
        if (formatTag.contains(".")) {
            String[] split = formatTag.split("\\.");
            for (String tag : split) {
                if (null == jsonObject) {
                    return;
                }
                Object obj = jsonObject.get(tag);
                if (obj instanceof JSONObject) {
                    jsonObject = jsonObject.getJSONObject(tag);
                } else if (obj instanceof JSONArray) {
                    log.info("解析到array类型");
                    return;
                } else {
                    log.info("匹配tag=[{}]的值={}", tag, JSON.toJSONString(obj));
                }
            }
        }
    }


    @Test
    public void testJsonObject() {
        List<LiteFlowChainModel> chains = Lists.newArrayList();
        chains.add(buildChainModel("chain1", "xxxxxx1"));
        chains.add(buildChainModel("chain2", "xxxxxx2"));
        JSONObject chainJSON = new JSONObject();
        chainJSON.put("chain", chains);
        JSONObject flowJSON = new JSONObject();
        flowJSON.put("flow", chainJSON);
        log.info("构造结果={}", flowJSON.toJSONString());
    }


    private LiteFlowChainModel buildChainModel(String name, String value) {
        LiteFlowChainModel chain = new LiteFlowChainModel();
        chain.setName(name);
        chain.setValue(value);
        return chain;
    }


}
