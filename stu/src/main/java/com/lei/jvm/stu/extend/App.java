package com.lei.jvm.stu.extend;

import lombok.extern.slf4j.Slf4j;

/**
 *  职能描述：
 *  @author leihaoyuan
 *  @version 2022/11/2 18:12
 */
@Slf4j
public class App {

    public static void main(String[] args) {
        SubOne one = new SubOne();
        one.addResource();
        SubTwo two = new SubTwo();
        two.addResource();
        log.info("比较={}", one.resource == two.resource);
        log.info(one.resource.toString());
        log.info(two.resource.toString());
    }
}
