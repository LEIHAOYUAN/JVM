package com.lei.design.pipeline;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author leihaoyuan
 * @Date 2021/12/10 10:04
 * @Version 1.0
 * @Description
 */
@Slf4j
public class PipelineClient {

    public static void main(String[] args) {
        NormalPipeLine normalPipeLine = new NormalPipeLine();
        Validator validator = new Validator();
        // 定义上下文
        PipeLineContext pipeLineContext = new PipeLineContext(0);
        pipeLineContext.put("index", "0");
        // 增加阀门
        normalPipeLine.addValve(validator); // 参数校验阀门
        // 管道执行
        FlowResult flowResult = normalPipeLine.start(pipeLineContext);
        log.info(JSON.toJSONString(flowResult));
    }

}
