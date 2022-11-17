package com.lei.jvm.spring.moudle.liteflow;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 *  职能描述：LiteFlow组件
 *  @author leihaoyuan
 *  @version 2022/10/9 13:15
 */
@Slf4j
@LiteflowComponent("B1")
public class B1cmp extends NodeComponent {
    @Override
    public void process() throws Exception {
        log.info("【B1】组件执行.............");
    }

    @Override
    public boolean isAccess() {
        return true;
    }
}
