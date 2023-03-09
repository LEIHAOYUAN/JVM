package com.lei.jvm.utils.base.utils.message;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;

/**
 *  职能描述：
 *  @author leihaoyuan
 *  @version 2022/11/11 13:37
 */
@Slf4j
public class MessageFormatUtil {

    public static void main(String[] args) {
        log.info(MessageFormat.format("数据关联字段：[{0}]，未选择业务对象", "test", "AAA"));
        log.info(MessageFormat.format("数据关联字段：''{0}''，未选择业务对象", "test"));
        log.info(MessageFormat.format("{0}字段已启用不能删除", "test"));
        log.info(MessageFormat.format("'{0}字段已启用不能删除'", "test"));
        log.info(MessageFormat.format("{0}字段已启用不能删除","AAA"));
        log.info(MessageFormat.format("{0}测试空集合-{1}", Lists.newArrayList()));
        log.info(MessageFormat.format("{0}测试空集合-{1}", Lists.newArrayList("AA","BB")));

    }
}
