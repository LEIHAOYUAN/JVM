package com.lei.jvm.spring.service;

import com.lei.jvm.spring.service.liteflow.LiteFlowService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *  职能描述：LiteFlow单元测试
 *  @author leihaoyuan
 *  @version 2022/10/9 13:05
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class LiteFlowServiceTest {

    @Autowired
    private LiteFlowService liteFlowService;

    @Test
    public void testExecute() {
        liteFlowService.execute();
    }

}
