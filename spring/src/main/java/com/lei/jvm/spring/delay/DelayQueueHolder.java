package com.lei.jvm.spring.delay;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.DelayQueue;

/**
 * @author leihaoyuan
 * @version 2023/11/3 9:50
 */
@Slf4j
@UtilityClass
public class DelayQueueHolder {


    private static final DelayQueue<DelayData> sourceIdDelayQuery = new DelayQueue<>();


    public static String takeDelaySourceId() {
        try {
            DelayData delayData = sourceIdDelayQuery.take();
            if (null != delayData) {
                String sourceId = delayData.getSourceId();
                log.info("获取到延时记录源ID=[{}]", sourceId);
                return sourceId;
            }
        } catch (InterruptedException ex) {
            log.error("获取延时数据异常={}", ex.getMessage(), ex);
        }
        return StringUtils.EMPTY;
    }


    public static void putDelaySourceId(String sourceId) {
        if (StringUtils.isBlank(sourceId)) {
            log.warn("待添加延时源ID为空");
            return;
        }
        log.info("添加延时记录源ID=[{}]", sourceId);
        sourceIdDelayQuery.offer(new DelayData(200000, sourceId));
    }


}
