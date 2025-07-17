package com.lei.jvm.stu.collection;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.lei.jvm.stu.clone.Student;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2020/11/23 15:27
 */
@Slf4j
public class ListTest {

    public static void main(String[] args) {
//        testComplexSort();
//        testRemove();
//        markElements(Lists.newArrayList("A", "B", "C"), Lists.newArrayList("B", "C", "D"));
        List<String> strings = limitStrings(Lists.newArrayList("1", "2", "3"), 100);
        log.info("result={}", JSON.toJSONString(strings));
    }

    public static Map<String, Integer> markElements(List<String> A, List<String> B) {
        // 创建两个不可修改的集合视图（避免修改原始数据）
        final Set<String> setA = Collections.unmodifiableSet(new HashSet<>(A));
        final Set<String> setB = Collections.unmodifiableSet(new HashSet<>(B));

        // 使用 Stream 处理所有元素
        Map<String, Integer> markMap = Stream.concat(A.stream(), B.stream())
            .distinct() // 去重（避免重复处理相同元素）
            .collect(Collectors.toMap(
                element -> element,            // 元素作为键
                element -> {                   // Lambda 计算状态值
                    boolean inA = setA.contains(element);
                    boolean inB = setB.contains(element);
                    return (inA && inB) ? 3 : (inA ? 1 : 2);
                },
                (existing, replacement) -> existing // 合并函数（不会执行）
            ));
        log.info("标记结果={}", JSON.toJSONString(markMap));
        return markMap;
    }


    private static void testRemove() {
        List<String> param = Lists.newArrayList("A", "A", "B", "C");
        param.remove("C");
        log.info("去除后结果={}", JSON.toJSONString(param));
    }

    private static void testComplexSort() {
        List<List<String>> allPath = Lists.newArrayList();
        allPath.add(buildList("A", "B", "D", "H"));
        allPath.add(buildList("A", "B"));
        allPath.add(buildList("A", "B", "D"));
        allPath.add(buildList("A", "C", "E", "F", "H"));
        allPath.add(buildList("A", "C"));
        allPath.add(buildList("A", "C", "E", "F"));
        allPath.add(buildList("A", "C", "E"));
        Collections.sort(allPath, (o1, o2) -> o1.size() > o2.size() ? 1 : -1);
        log.info("排序结果={}", JSON.toJSONString(allPath));
    }

    private static List<String> buildList(String... args) {
        return Lists.newArrayList(args);
    }


    private static void testRemoveAll() {
        List<String> param = Lists.newArrayList();
        param.add("AA7888.56959E+45645646");
        param.add("AAAE");
        param.add("AAAE5+");
        param.add("AAA+");
        param.add("BBBe+");

        List<String> failList = param.stream().filter(i -> JSON.toJSONString(i).contains("===")).collect(Collectors.toList());

        param.removeAll(failList);
        param.removeAll(Lists.newArrayList());
//        param.removeAll(null);
        log.info("移除后集合：{}", JSON.toJSONString(param));
    }

    private static void testIterator() {
        List<String> param = Lists.newArrayList();
        param.add("AA7888.56959E+45645646");
        param.add("AAAE");
        param.add("AAAE5+");
        param.add("AAA+");
        param.add("BBBe+");
        Iterator<String> iterator = param.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().contains("E+")) {
                iterator.remove();
            }
        }
        log.info("过滤后集合：{}", JSON.toJSONString(param));
    }


    private static void testFilter() {
        Student s1 = new Student(200);
        Student s2 = new Student(300);
        Student s3 = new Student(400);
        List<Student> param = Lists.newArrayList();
        param.add(s1);
        param.add(s2);
        param.add(s3);
        BigDecimal total = param.stream().filter(i -> null != i.getAmount() && null != i.getVolume()).reduce(new BigDecimal(0), (x, y) -> x.add(y.getVolume().multiply(y.getAmount()).divide(BigDecimal.TEN, 7, RoundingMode.HALF_UP)), BigDecimal::add);

        log.info("汇总总数量：{}", JSON.toJSONString(total));

    }

    private static List<String> limitStrings(List<String> values, int limit) {
        return values.stream().filter(StringUtils::isNotBlank).limit(limit).collect(Collectors.toList());
    }

    private static void testSplit() {
        List<Long> param = Lists.newArrayList();
        for (int i = 0; i < 800; i++) {
            param.add(i * 1L);
        }
        int size = param.size() / 3 + 1;
        List<List<Long>> partition = ListUtils.partition(param, size);
        log.info("分组大小：{}，分组结果：{}", size, JSON.toJSONString(partition));
    }

    private static void testToMap() {
        List<Student> listStu = Lists.newArrayList();
//        listStu.add(new Student(null));
//        listStu.add(new Student(1));

        Map<Integer, Student> collect = listStu.stream().collect(Collectors.toMap(Student::getId, s -> s));
        System.out.println(collect == null);
        System.out.println(JSON.toJSONString(collect));
    }


    private static void testRemove1() {
        List<String> param = Lists.newArrayList(Arrays.asList("AAA", "AA", "ABEC"));
        log.info("移除前：{}", JSON.toJSONString(param));
        Iterator<String> iterator = param.iterator();
        while (iterator.hasNext()) {
            if ("AAA".equals(iterator.next())) {
                iterator.remove();
            }
        }
        log.info("移除后：{}", JSON.toJSONString(param));
    }

    private static void testRemove2() {
        List<String> param = Lists.newArrayList(Arrays.asList("A", "AAA", "GDFES"));
        log.info("移除前：{}", JSON.toJSONString(param));
        param.removeIf("AAA"::equals);
        log.info("移除后：{}", JSON.toJSONString(param));
    }

    private static void testSort() {
        List<Long> list = Lists.newArrayList();
        list.add(null);
        list.add(null);
        list.add(20L);
        list.add(8L);
        list.add(1L);
        list.add(56L);
        list.add(-3L);
        log.info("排序前：{}", JSON.toJSONString(list));
        if (list.contains(null)) {
            log.info("包含空值，无法排序");
        }
        list = list.stream().filter(Objects::nonNull).collect(Collectors.toList());
        Collections.sort(list);
        log.info("排序后：{}", JSON.toJSONString(list));
    }

    private static void testMultiFieldSort() {
        List<Student> listStu = Lists.newArrayList();
        listStu.add(new Student(1, "AAA"));
        listStu.add(new Student(6, "DDD"));
        listStu.add(new Student(3, "AAA"));
        listStu.add(new Student(2, "AAA"));
        listStu.add(new Student(5, "CCC"));
        listStu.add(new Student(4, "BBB"));

        listStu = listStu.stream().sorted(Comparator.comparing(Student::getId).thenComparing(Student::getName)).collect(Collectors.toList());
        log.info("排序结果={}", JSON.toJSONString(listStu));
    }

    private static void testDistinct() {
        List<Student> listStu = Lists.newArrayList();
        listStu.add(new Student(null));
        listStu.add(new Student(1));

        List<Integer> ids = listStu.stream().map(Student::getId).distinct().collect(Collectors.toList());
        System.out.println(JSON.toJSONString(ids));
        System.out.println(ids.size() == listStu.size());
    }


    /**
     * list转换map，key重复问题
     */
    private static void testDealMapDuplicateKey() {
        List<Student> listStu = Lists.newArrayList();
        listStu.add(null);
//        listStu.add(new Student(null));
        listStu.add(new Student(6));
//        listStu.add(new Student(8));
//        listStu.add(new Student(10));
//        listStu.add(new Student(11));
//        listStu.add(new Student(12));
        listStu = listStu.stream().filter(Objects::nonNull).collect(Collectors.toList());
        Map<Integer, Student> idMap = listStu.stream().collect(Collectors.toMap(Student::getId, s -> s, (oldValue, newValue) -> newValue));
        System.out.println(JSON.toJSONString(idMap));
    }

    private static void testMapDuplicateKey() {
        List<Student> listStu = Lists.newArrayList();
        listStu.add(new Student(8));
        listStu.add(new Student(10));
        listStu.add(new Student(11));
        listStu.add(new Student(12));
        Map<Integer, String> idMap = listStu.stream().filter(i -> StringUtils.isNotBlank(i.getName())).collect(Collectors.toMap(Student::getId, Student::getName));
        System.out.println(JSON.toJSONString(idMap));
    }

    private static void testModifyStream() {
        List<Student> listStu = Lists.newArrayList();
        listStu.add(new Student(10));
        Map<Integer, Student> idMap = listStu.stream().collect(Collectors.toMap(Student::getId, s -> s));

        idMap.get(10).setId(500);
        log.info("修改后集合：{}", JSON.toJSONString(listStu));
    }


    /**
     * 根据一个集合过滤另一个集合
     */
    private static void testCollectionInster() {
        List<Integer> conditionList = Lists.newArrayList();
        conditionList.add(10);
        conditionList.add(null);


        List<Student> listStu = getStudents();

        System.out.println("过滤前：" + JSON.toJSONString(listStu));

        listStu = listStu.stream().filter(item -> {
            return !conditionList.contains(item.getId());
        }).collect(Collectors.toList());

        System.out.println("过滤后：" + JSON.toJSONString(listStu));
    }

    private static List<Student> getStudents() {
        List<Student> listStu = Lists.newArrayList();
        listStu.add(new Student(5, "AAA"));
        listStu.add(new Student(2, "AAA"));
        listStu.add(new Student(9, "AAA"));
        listStu.add(new Student(0, "AAA"));
        listStu.add(new Student(1, "AAA"));
        listStu.add(new Student(9, "BBB"));
        listStu.add(new Student(8, "BBB"));
        listStu.add(new Student(4, "BBB"));
        listStu.add(new Student(3, "BBB"));
        listStu.add(new Student(2, "BBB"));
        listStu.add(new Student(7, "BBB"));
        return listStu;
    }


    private static void groupThenSortKey() {
        List<Student> records = getStudents();
        TreeMap<Integer, List<Student>> collect = records.stream().collect(Collectors.groupingBy(Student::getId, TreeMap::new, Collectors.toList()));
    }

    private static void groupThenSortValue() {
        List<Student> records = getStudents();
        Map<String, List<Student>> collect = records.stream().sorted(Comparator.comparing(Student::getId)).collect(Collectors.groupingBy(Student::getName, Collectors.toList()));
        log.info("先分组后排序结果={}", JSON.toJSONString(collect));
    }
}
