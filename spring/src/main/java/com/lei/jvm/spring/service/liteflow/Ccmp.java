package com.lei.jvm.spring.service.liteflow;

import com.yomahub.liteflow.core.NodeComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 *  职能描述：LiteFlow组件
 *  @author leihaoyuan
 *  @version 2022/10/9 13:15
 */
@Slf4j
@Component("c")
public class Ccmp extends NodeComponent {
    @Override
    public void process() throws Exception {
        log.info("【c】组件执行.............");
    }
}
