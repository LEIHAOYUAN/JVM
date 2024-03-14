package com.lei.jvm.stu.collection.number;

import cn.hutool.core.util.NumberUtil;
import com.google.common.collect.Lists;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author leihaoyuan
 * @version 2024/3/14 15:39
 */
@Slf4j
@UtilityClass
public class CommNumberUtil {

    public static void main(String[] args) {
        testIsNumber();
    }


    public void testIsNumber() {
        List<String> param = Lists.newArrayList("", "a", "8555555555555555555", "-78", "99999d", "0002536");
        for (String item : param) {
            log.info("[{}]是否是数字[{}]", item, NumberUtil.isNumber(item));
        }
    }


}
