package com.lei.jvm.spring;

import com.yomahub.liteflow.core.FlowExecutor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 *  职能描述：LiteFlow单元测试
 *  @author leihaoyuan
 *  @version 2022/10/9 13:05
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class LiteFlowSpringTest {

    @Resource
    private FlowExecutor flowExecutor;

    @Test
    public void testListFlow() {
        try {
            flowExecutor.execute("testChain", null);
        } catch (Exception e) {
            log.error("流程执行异常={}", e.getMessage(), e);
        }
    }

}
