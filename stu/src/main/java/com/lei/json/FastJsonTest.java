package com.lei.json;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 *  职能描述：
 *  @author leihaoyuan
 *  @version 2022/9/19 14:27
 */

@Slf4j
public class FastJsonTest {

    public static void main(String[] args) {
        Demo demo = new Demo();
        demo.setName("AAA");
        log.info("json格式：{}", JSON.toJSONString(demo));

        String demoJson = "{\"name\":\"AAA\"}";
        Object o = JSON.parseObject(demoJson, getDemoClass());
    }


    public static Class getDemoClass() {
        return Demo.class;
    }

    static class Demo {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
