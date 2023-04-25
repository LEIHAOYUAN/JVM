package com.lei.jvm.spring;

import com.alibaba.fastjson.JSON;
import com.lei.jvm.spring.dto.LocalLangItemResourceDTO;
import com.lei.jvm.spring.utils.ParseLocalResourceUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 *  职能描述：
 *  @author leihaoyuan
 *  @version 2022/11/14 9:40
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ParseYamlResourceTest {

    @Test
    public void testParseYml(){

        String jarResourcePath = "classpath*:lang/extra_messages*.yml";

        String localResourcePath = "classpath:lang/messages*.yml";

        List<LocalLangItemResourceDTO> result = ParseLocalResourceUtil.parseResource(localResourcePath);
        log.info("解析结果={}", JSON.toJSONString(result));
    }

}
