package com.lei.jvm.spring;

import com.alibaba.fastjson.JSONObject;
import com.lei.jvm.spring.dto.liteflow.LiteFlowChainModel;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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
