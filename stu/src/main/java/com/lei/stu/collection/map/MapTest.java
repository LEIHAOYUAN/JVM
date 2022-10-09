package com.lei.stu.collection.map;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.lei.stu.stream.Student;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author leihaoyuan
 * @Date 2021/3/29 11:20
 * @Version 1.0 @Description map操作
 */
@Slf4j
public class MapTest {

    public static void main(String[] args) {
//        testFilter();
//        testCurrentMap();
        testMinValue();
    }

    private static void testMinValue() {
        Map<String, Student> map = Maps.newConcurrentMap();
        Student s1 = new Student();
        s1.setName("AAA");
        s1.setCreateTime(1);

        Student s2 = new Student();
        s2.setName("BBB");
        s2.setCreateTime(1);

        map.put(s1.getName(), s1);
        map.put(s2.getName(), s2);

        log.info("移除前={}",JSON.toJSONString(map));
        String name = map.values().stream().min(Comparator.comparing(Student::getCreateTime)).get().getName();
        log.info("获取到最小值：name={}",name);
        map.remove(name);
        log.info("移除后结果：{}",JSON.toJSONString(map));

    }


    private static void test(int param) {
        log.info("参数：{}", param);
    }

    private static void testContains() {
        Map<String, String> param = Maps.newHashMap();
        param.put("AAa", "AAa");
        if (param.containsKey("A")) {
            log.info("包含测试通过");
        }
    }


    private static void testAdd() {
        Map<String, String> param = Maps.newHashMap();
        param.put("AA", "AA");
        param.put("AA", "AA");
        param.put("AA", "AA");

        Map<String, String> param1 = Maps.newHashMap();
        param1.put("AA", "AA1");
        param1.put("AA", "AA1");
        param1.put("AA", "AA1");

        Map<String, String> res = Maps.newHashMap();
        res.putAll(param);
        res.putAll(param1);
        log.info("最终结果：{}", JSON.toJSONString(res));
    }

    private static void testMaps() {
        HashMap<Object, Object> objectObjectHashMap = Maps.newHashMap(null);
        log.info("构造空值map：{}", JSON.toJSONString(objectObjectHashMap));
    }

    private static void testSort() {
        Map<String, BigDecimal> batchMap = Maps.newHashMap();
        batchMap.put("20210501", BigDecimal.TEN);
        batchMap.put("20200501", BigDecimal.TEN);
        batchMap.put("20190501", BigDecimal.TEN);
        batchMap.put("20210801", BigDecimal.TEN);
        batchMap.put("20210830", BigDecimal.TEN);
        log.info("排序前：{}", JSON.toJSONString(batchMap));
        batchMap = batchMap.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        log.info("排序后：{}", JSON.toJSONString(batchMap));
    }

    private static void testSum() {
        Map<String, BigDecimal> batchMap = Maps.newHashMap();
        batchMap.put("20210501", BigDecimal.TEN);
        batchMap.put("20200501", BigDecimal.TEN);
        BigDecimal sumNumber = BigDecimal.valueOf(batchMap.values().stream().mapToDouble(BigDecimal::doubleValue).sum());
        log.info("map-value求和结果：{}", sumNumber.toPlainString());
    }


    private static void testCurrentMap() {
        String json = "{\"key1\":\"test01\",\"key2\":\"test02\"}";
        Map<Long, String> map = Maps.newConcurrentMap();
        log.info("map结果：{}", map.get(null));
    }

    private static void testFilter() {
        Map<String, String> param = Maps.newHashMap();
        param.put(null, "TEST%");
        param.put("key1", "test01");
        param.put("key2", "test02");
        param.put("key2", "test02");
        log.info("过滤前---------------------map数据：{}", JSON.toJSONString(param));
        Map<String, String> filterMap = param.entrySet().stream().filter((i) -> StringUtils.isNotBlank(i.getKey())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        log.info("过滤后---------------------map数据：{}", JSON.toJSONString(filterMap));
    }


}
