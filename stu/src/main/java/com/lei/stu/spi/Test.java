package com.lei.stu.spi;

import sun.misc.Service;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @Author leihaoyuan
 * @Date 2021/3/29 18:04
 * @Version 1.0
 * @Description SPI服务测试
 */
public class Test {

    public static void main(String[] args) {
        // 方式1
        Iterator<SPIService> providers = Service.providers(SPIService.class);
        while (providers.hasNext()) {
            SPIService ser = providers.next();
            ser.execute();
        }

        System.out.println("--------------------------------");
        // 方式2
        ServiceLoader<SPIService> load = ServiceLoader.load(SPIService.class);
        Iterator<SPIService> iterator = load.iterator();
        while (iterator.hasNext()) {
            SPIService ser = iterator.next();
            ser.execute();
        }

    }


}
