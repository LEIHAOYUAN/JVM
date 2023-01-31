package com.lei.jvm.stu.collection.map.pojo;

import lombok.extern.slf4j.Slf4j;

/**
 *  职能描述：
 *  @author leihaoyuan
 *  @version 2023/1/31 14:52
 */
@Slf4j
public class Test {

    public static void main(String[] args) {
        Struct aaa = buildStruct("AAA");

        ExtendStruct extendStruct = (ExtendStruct) aaa;
        log.info(extendStruct.getDefaultValue());
    }


    private static Struct buildStruct(String name) {
        ExtendStruct result = new ExtendStruct();
        result.setName(name);
        result.setDefaultValue("默认值测试");
        return result;
    }


}
