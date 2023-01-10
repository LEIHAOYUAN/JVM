package com.lei.jvm.spring;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lei.jvm.json.Person;
import com.lei.jvm.json.Student;
import com.lei.jvm.json.Teacher;
import com.lei.jvm.spring.dto.liteflow.LiteFlowChainModel;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Maps;
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
    public void testNestJSON(){
        Map<String,Object> maps = new HashMap<>();
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
        log.info("转换结果={}",jsonObject.toJSONString());
    }

    @Test
    public void testNestMap(){
        Map<String,Object> maps = new HashMap<>();
        maps.put("name","人类");
        maps.put("teacher.name","教师");
        maps.put("teacher.students.name","学生");

        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(maps));
        log.info("解析JSONobject");
    }


    @Test
    public void testJsonObject() {

        List<LiteFlowChainModel> chains = Lists.newArrayList();
        chains.add(buildChainModel("chain1", "xxxxxx1"));
        chains.add(buildChainModel("chain2", "xxxxxx2"));
        JSONObject chainJSON = new JSONObject();
        chainJSON.put("chain", chains);
        JSONObject flowJSON = new JSONObject();
        flowJSON.put("flow",  chainJSON);
        log.info("构造结果={}", flowJSON.toJSONString());
    }


    private LiteFlowChainModel buildChainModel(String name, String value) {
        LiteFlowChainModel chain = new LiteFlowChainModel();
        chain.setName(name);
        chain.setValue(value);
        return chain;
    }


}
