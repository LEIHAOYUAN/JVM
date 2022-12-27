package com.lei.jvm.spring.liteflow;

import com.yomahub.liteflow.builder.el.LiteFlowChainELBuilder;
import com.yomahub.liteflow.core.FlowExecutor;
import lombok.extern.slf4j.Slf4j;
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

    @Test
    public void test() {
//            测试空分支
//        LiteFlowChainELBuilder.createChain().setChainName("test").setEL("SWITCH(SwitchCmp)").build();
//        LiteFlowChainELBuilder.createChain().setChainName("test_chain").setEL("B1=THEN(B1,B2);WHEN(THEN(A1,A2,A3),B1).ignoreError(true);").build();
        // PRE/FINALLY实际未执行
//        LiteFlowChainELBuilder.createChain().setChainName("test_chain").setEL("temp=THEN(THEN(PRE(A3),B2,FINALLY(A1)),B1);THEN(temp);").build();
//        LiteFlowChainELBuilder.createChain().setChainName("test_chain").setEL("chain=THEN(THEN(PRE(A3),B1,FINALLY(A1)),THEN(A2,B2));THEN(chain);").build();
//        LiteFlowChainELBuilder.createChain().setChainName("test_chain").setEL("chain=THEN(PRE(A3),B1,FINALLY(A1));THEN(A2,B2);THEN(chain);").build();


//        LiteFlowChainELBuilder.createChain().setChainName("sub_chain").setEL("test1=FINALLY(A1);").build();
//        LiteFlowChainELBuilder.createChain().setChainName("tran_chain").setEL("test2=THEN(PRE(A3),B1,sub_chain);").build();
//        LiteFlowChainELBuilder.createChain().setChainName("test_chain").setEL("test3=THEN(B2,tran_chain);").build();

        LiteFlowChainELBuilder.createChain().setChainName("test_chain").setEL("B1=THEN(B1,B2);WHEN(THEN(A1,A2,A3),B1).ignoreError(true);").build();

        try {
            flowExecutor.execute("el_chain", null);
        } catch (Exception e) {
            log.error("流程执行异常={}", e.getMessage(), e);
        }
    }


}
