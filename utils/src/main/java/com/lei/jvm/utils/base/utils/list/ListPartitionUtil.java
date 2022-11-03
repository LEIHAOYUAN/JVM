package com.lei.jvm.utils.base.utils.list;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *  职能描述：集合切分工具类
 *  @author leihaoyuan
 *  @version 2022/11/3 10:08
 *  https://www.cnblogs.com/zhanh247/p/11842748.html
 */
@Slf4j
public class ListPartitionUtil {

    public static void main(String[] args) {
        List<String> param = new ArrayList<>();
        List<List<String>> lists = partitionByApache(param, 100);
        for (List<String> list : lists) {
            log.info("分组结果={}", JSON.toJSONString(list));
        }
    }

    /**
     * 存java处理
     * @param param
     * @param size
     * @return
     * @param <T>
     */
    public static <T> List<List<T>> partitioByBase(final List<T> param, final int size) {
        if (CollectionUtils.isEmpty(param)) {
            return Lists.newArrayList();
        }
        List<List<T>> result = new ArrayList<List<T>>();
        final int listSize = param.size();
        for (int i = 0; i < listSize; i += size) {
            List<T> subList = null;
            if (i + size > listSize) {
                subList = param.subList(i, i + listSize - i);
            } else {
                subList = param.subList(i, i + size);
            }
            result.add(subList);
        }
        return result;
    }


    /**
     * 使用guava工具类
     * @param param
     * @param size
     * @return
     * @param <T>
     */
    public static <T> List<List<T>> partitionByGuava(List<T> param, int size) {
        if (CollectionUtils.isEmpty(param)) {
            return Lists.newArrayList();
        }
        return Lists.partition(param, size);
    }

    /**
     * 使用apache工具类
     * @param param
     * @param size
     * @return
     * @param <T>
     */
    public static <T> List<List<T>> partitionByApache(List<T> param, int size) {
        if (CollectionUtils.isEmpty(param)) {
            return Lists.newArrayList();
        }
        return ListUtils.partition(param, size);
    }

    /**
     * 使用流遍历操作
     * @param param
     * @param size
     * @return
     * @param <T>
     */
    private static <T> List<List<T>> partitionBySimpleStream(final List<T> param, final int size) {
        if (CollectionUtils.isEmpty(param)) {
            return Lists.newArrayList();
        }
        Integer limit = (param.size() + size - 1) / size;
        List<List<T>> mglist = new ArrayList<List<T>>();
        Stream.iterate(0, n -> n + 1).limit(limit).forEach(i -> {
            mglist.add(param.stream().skip(i * size).limit(size).collect(Collectors.toList()));
        });
        return mglist;
    }

    /**
     * 使用流并行处理
     * @param param
     * @param size
     * @return
     * @param <T>
     */
    private static <T> List<List<T>> partitionByParallelStream(final List<T> param, final int size) {
        if (CollectionUtils.isEmpty(param)) {
            return Lists.newArrayList();
        }
        Integer limit = (param.size() + size - 1) / size;
        List<List<T>> splitList = Stream.iterate(0, n -> n + 1).limit(limit).parallel()
                .map(a -> param.stream().skip(a * size).limit(size).parallel().collect(Collectors.toList()))
                .collect(Collectors.toList());
        return splitList;
    }


}
