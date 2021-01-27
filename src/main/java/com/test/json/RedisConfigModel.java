package com.test.json;

import com.alibaba.fastjson.JSON;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author leihaoyuan
 * @Date 2021/1/23 13:00
 * @Version 1.0
 * @Description
 */
@Getter
@Setter
public class RedisConfigModel implements Serializable {

    private static final long serialVersionUID = 233254677075980897L;

    private String redisHost;
    private Integer redisPort;
    private Long redisTimeout;
    private Integer jedisPoolMaxActive;
    private Integer jedisPoolMaxIdel;
    private Integer jedisPoolMinIdel;
    private Integer jedisPoolMaxWait;


    public static void main(String[] args) {
        RedisConfigModel redisConfigModel = new RedisConfigModel();
        redisConfigModel.setRedisHost("10.88.128.109");
        redisConfigModel.setRedisPort(6379);
        redisConfigModel.setRedisTimeout(3000L);
        redisConfigModel.setJedisPoolMaxActive(8);
        redisConfigModel.setJedisPoolMaxIdel(8);
        redisConfigModel.setJedisPoolMinIdel(0);
        redisConfigModel.setJedisPoolMaxWait(-1);
        System.out.println(JSON.toJSONString(redisConfigModel));
        System.out.println("revert commit000000000");
        System.out.println("revert commit");

    }


}
