package com.lei.jvm.stu.base;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author leihaoyuan
 * @version 2023/10/23 18:54
 */
@Slf4j
public class LongTest {

    public static void main(String[] args) {
        parseLong();
    }


    private static void parseLong() {
        List<Long> result = Lists.newArrayList();
        List<String> param = Lists.newArrayList(" 8 ","","AAA", "78678835436546","1234568888888888L", "_NONE_","8888888");
        for (String item : param) {
            try {
                result.add(Long.parseLong(item));
            } catch (Exception ex) {
                log.warn("过滤非自定义数据权限项=[{}]", item);
            }
        }
        log.info("自定义权限配置结果={}", JSON.toJSONString(result));
    }


}
