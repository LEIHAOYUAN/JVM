package com.lei.design.pipeline;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Validator extends NormalValve {
    @Override
    public FlowResult invoke(PipeLineContext pipeLineContext) {
        pipeLineContext.put("param", "1");
        return processContinue(pipeLineContext);
    }
}