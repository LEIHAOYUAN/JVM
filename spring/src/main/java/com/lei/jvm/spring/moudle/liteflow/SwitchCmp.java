package com.lei.jvm.spring.moudle.liteflow;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeSwitchComponent;
import lombok.extern.slf4j.Slf4j;

/**
 *  职能描述：
 *  @author leihaoyuan
 *  @version 2022/11/23 16:33
 */
@Slf4j
@LiteflowComponent("SwitchCmp")
public class SwitchCmp extends NodeSwitchComponent {
    @Override
    public String processSwitch() throws Exception {
        return "A1";
    }
}
