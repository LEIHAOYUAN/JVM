package com.lei.jvm.stu.system;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

/**
 * @Author leihaoyuan
 * @Date 2021/2/3 16:27
 * @Version 1.0
 * @Description
 */
@Slf4j
public class JVMSysTest {

    public static void main(String[] args) {
        log.info("核心数={}", Runtime.getRuntime().availableProcessors());
    }

    private static void property() {
        System.out.println(Integer.MAX_VALUE);

        Properties properties = System.getProperties();
        System.out.println(JSON.toJSONString(properties, true));
    }

}
