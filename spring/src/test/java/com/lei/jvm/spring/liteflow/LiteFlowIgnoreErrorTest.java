package com.lei.jvm.spring.liteflow;

import com.yomahub.liteflow.builder.el.LiteFlowChainELBuilder;
import com.yomahub.liteflow.core.FlowExecutor;
import lombok.extern.slf4j.Slf4j;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 *  职能描述：
 *  @author leihaoyuan
 *  @version 2022/11/17 13:24
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class LiteFlowIgnoreErrorTest {

    @Resource
    private FlowExecutor flowExecutor;

//    @BeforeClass
//    public static void init(){
//        LiteFlowChainELBuilder chain = LiteFlowChainELBuilder.createChain();
//        chain.setChainId("TEST");
//        chain.setEL("WHEN(THEN(A1,A2,A3),THEN(B1,B2)).ignoreError(true)");
//    }

    @Test
    public void test() {
        try {
            flowExecutor.execute("test", null);
        } catch (Exception e) {
            log.error("流程执行异常={}", e.getMessage(), e);
        }
    }


}
