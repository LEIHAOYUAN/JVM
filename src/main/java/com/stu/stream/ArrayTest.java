package com.stu.stream;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author leihaoyuan
 * @Date 2021/5/11 17:15
 * @Version 1.0
 * @Description 集合常用操作中的坑
 */
@Slf4j
public class ArrayTest {

    public static void main(String[] args) {
        correctForError23();
    }



    private static void test1(){
        error1();
        correct1ForError1();
        correct2ForError1();
    }

    /**
     * Arrays.asList 不能用来直接转换基本类型数组，会将基本类型数组整体作为集合的一个元素
     */
    private static void error1(){
        int[] arr = {1,2,3};
        // 整个数组被当做list的一个元素
        List list = Arrays.asList(arr);
        log.info("list：{},size：{}，class：{}",list,list.size(),list.get(0).getClass());
    }

    /**
     * 使用包装类型解决
     */
    private static void correct1ForError1(){
        Integer[] arr = {1,2,3};
        List list = Arrays.asList(arr);
        log.info("list：{},size：{}，class：{}",list,list.size(),list.get(0).getClass());
    }

    /**
     * 使用Arrays.stream方式解决
     */
    private static void correct2ForError1(){
        int[] arr = {1,2,3};
        List list = Arrays.stream(arr).boxed().collect(Collectors.toList());
        log.info("list：{},size：{}，class：{}",list,list.size(),list.get(0).getClass());
    }


    /**
     * Arrays.asList 返回的 List 并不是
     * 我们期望的 java.util.ArrayList，而是 Arrays 的内部类 ArrayList
     * 修改源数组中的元素，list中的元素也会被修改
     */
    private static void error2(){
        String[] arr = {"1","2","3"};
        List list = Arrays.asList(arr);
        arr[1] = "4";
        log.info("list：{},size：{}，class：{}",list,list.size(),list.get(0).getClass());
    }

    /**
     * 重新new一个list集合解决共用数组问题
     */
    private static void correctForError23(){
        String[] arr = {"1","2","3"};
        List list = Lists.newArrayList(Arrays.asList(arr));
        arr[1] = "4";
        list.add("5");
        log.info("list：{},size：{}，class：{}",list,list.size(),list.get(0).getClass());
    }

    /**
     * Arrays.asList 返回的 List 不支持增删操作
     */
    private static void error3(){
        String[] arr = {"1","2","3"};
        List list = Arrays.asList(arr);
        list.add("5");
        log.info("list：{},size：{}，class：{}",list,list.size(),list.get(0).getClass());
    }







}
