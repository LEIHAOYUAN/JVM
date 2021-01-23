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



//    redis:
//    host: ${maven.spring.redis.host}
//    port: ${maven.spring.redis.port}
//    timeout: ${maven.spring.redis.timeout}
//    jedis:
//    pool:
//    max-active: ${maven.spring.redis.jedis.pool.max-active}
//    max-idle: ${maven.spring.redis.jedis.pool.max-idle}
//    min-idle: ${maven.spring.redis.jedis.pool.min-idle}
//    max-wait: ${maven.spring.redis.jedis.pool.max-wait}

    private String redisHost;
    private Integer redisPort;
    private Long redisTimeout;
    private Integer jedisPoolMaxActive;
    private Integer jedisPoolMaxIdel;
    private Integer jedisPoolMinIdel;
    private Integer jedisPoolMaxWait;


    public static void main(String[] args) {
        RedisConfigModel redisConfigModel = new RedisConfigModel();
        //    maven.spring.redis.host=10.88.128.109
//    maven.spring.redis.port=6379
//    maven.spring.redis.timeout=3000
//    maven.spring.redis.jedis.pool.max-active=8
//    maven.spring.redis.jedis.pool.max-idle=8
//    maven.spring.redis.jedis.pool.min-idle= 0
//    maven.spring.redis.jedis.pool.max-wait=-1
        redisConfigModel.setRedisHost("10.88.128.109");
        redisConfigModel.setRedisPort(6379);
        redisConfigModel.setRedisTimeout(3000L);
        redisConfigModel.setJedisPoolMaxActive(8);
        redisConfigModel.setJedisPoolMaxIdel(8);
        redisConfigModel.setJedisPoolMinIdel(0);
        redisConfigModel.setJedisPoolMaxWait(-1);
        System.out.println(JSON.toJSONString(redisConfigModel));
    }



}
