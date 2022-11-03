package com.lei.jvm.stu.extend;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 *  职能描述：
 *  @author leihaoyuan
 *  @version 2022/11/2 18:12
 */
@Slf4j
public class App {

    private static final List<String> data = Lists.newArrayList();


    public static void main(String[] args) {
        SubOne one = new SubOne();
        one.addResource();
        SubTwo two = new SubTwo();
        two.addResource();
        log.info("比较={}", one.resource == two.resource);
        log.info(one.resource.toString());
        log.info(two.resource.toString());
        log.info("-------------------------------------------------------");
        List<String> data1 = getData();
        data1.add("TEST");

        log.info("result={}", JSON.toJSONString(data));
    }


    private static List<String> getData(){
        return data;
    }



}
