package com.lei.jvm.stu.collection.map;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lei.jvm.stu.stream.Student;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @Author leihaoyuan
 * @Date 2021/3/29 11:20
 * @Version 1.0 @Description map操作
 */
@Slf4j
public class MapTest {

    public static void main(String[] args) {
        buildSQL(99);
    }

    public static void buildSQL(int type) {
        switch (type) {
            case 0:
                break;
            case 1:
                break;
            default:
                log.info("默认参数处理----------------");
                return;
        }
        log.info("其他执行逻辑处理");
    }


    public static void testFlatValue() {
        Map<String, List<Integer>> param = Maps.newHashMap();

        param.put("AAA", Lists.newArrayList(1, 2, 3, 4, 5));
        param.put("BBB", Lists.newArrayList(6, 7, 8, 9, 10));

        List<Integer> collect = param.values().stream().flatMap(Collection::stream).collect(Collectors.toList());
        log.info("所有结果={}", JSON.toJSONString(collect));

    }


    private static void testTreeMap(String key) {
        Map<String, Long> sourceTargetMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        sourceTargetMap.put("admin", 99L);
        sourceTargetMap.put("Admin", 88L);
        log.info("查询结果={}", sourceTargetMap.get(key));
    }


    private static void testPutAll() {
        Map<Object, Object> paramMap = Maps.newHashMap();
        paramMap.put("AAA", "1111");

        Map<Object, Object> subMap = Maps.newHashMap();
        subMap.put("AAA", "222");
        subMap.put("A", "666");

        paramMap.putAll(subMap);
        log.info("添加结果={}", paramMap);
    }

    private static void testIterator() {
        Map<Integer, String> param = new HashMap<>();
        param.put(1, "AAA");
        param.put(2, "bbb");
        Iterator<Map.Entry<Integer, String>> iterator = param.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            if ("AAA".equals(entry.getValue())) {
                log.warn("语言编号={}不存在或已失效！", entry.getValue());
                iterator.remove();
            }
        }
        log.info("迭代后={}", param);
    }


    private static void testCover() {
        Map<String, String> map1 = new HashMap<>();
        map1.put("A", "A1");
        Map<String, String> map2 = new HashMap<>();
        map2.put("A", "A2");
        Map<String, String> map3 = new HashMap<>();
        map3.put("A", "A3");
        Map<String, String> map = new HashMap<>();
        map.putAll(map2);
        map.putAll(map3);
        map.putAll(map1);
        log.info("最终结果={}", JSON.toJSONString(map));
    }

    private static void testMinValue() {
        Map<String, Student> map = buildCurrentMapData();

        log.info("移除前={}", JSON.toJSONString(map));
        String name = map.values().stream().min(Comparator.comparing(Student::getCreateTime)).get().getName();
        log.info("获取到最小值：name={}", name);
        map.remove(name);
        log.info("移除后结果：{}", JSON.toJSONString(map));

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

    private static void testRemove() {
        Map<String, Student> stringStudentMap = buildCurrentMapData();
        stringStudentMap.remove("AAA");
        log.info("测试移除结果={}", JSON.toJSONString(stringStudentMap));
    }

    private static Map<String, Student> buildCurrentMapData() {
        Map<String, Student> map = Maps.newConcurrentMap();
        Student s1 = new Student();
        s1.setName("AAA");
        s1.setCreateTime(1);

        Student s2 = new Student();
        s2.setName("BBB");
        s2.setCreateTime(1);

        map.put(s1.getName(), s1);
        map.put(s2.getName(), s2);
        return map;
    }


}
