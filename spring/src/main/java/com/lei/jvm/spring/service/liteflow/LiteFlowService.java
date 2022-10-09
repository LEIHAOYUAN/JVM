package com.lei.jvm.spring.service.liteflow;

import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.flow.LiteflowResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *  职能描述：LiteFlow流程组装
 *  @author leihaoyuan
 *  @version 2022/10/9 13:03
 */
@Slf4j
@Service
public class LiteFlowService {

    @Autowired
    private FlowExecutor flowExecutor;

    public void execute() {
        log.info("执行组件............................");
        LiteflowResponse response = flowExecutor.execute2Resp("chain1", "arg");
    }

}
