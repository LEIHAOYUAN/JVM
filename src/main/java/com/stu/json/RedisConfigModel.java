package com.stu.json;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

/**
 * @Author leihaoyuan
 * @Date 2021/1/23 13:00
 * @Version 1.0
 * @Description
 */
@Slf4j
@Data
public class RedisConfigModel implements Serializable {

    private static final long serialVersionUID = 233254677075980897L;

    private String redisHost;
    private Integer redisPort;
    private Long redisTimeout;
    private Integer jedisPoolMaxActive;
    private Integer jedisPoolMaxIdel;
    private Integer jedisPoolMinIdel;
    private Integer jedisPoolMaxWait;

    private String name;
    private Integer age;
    private Integer sex;

    private Map<String, BigDecimal> map;


    public static void main(String[] args) {
        RedisConfigModel redisConfigModel = new RedisConfigModel();
        Map<String, BigDecimal> map = Maps.newHashMap();
        map.put("909009",BigDecimal.ONE);
        map.put("09090",null);
        redisConfigModel.setMap(map);
        log.info(map.toString());
        log.info(redisConfigModel.toString());
        BigDecimal decimal = null;
        log.error(String.valueOf(BigDecimal.ONE.compareTo(decimal)));

    }


}
