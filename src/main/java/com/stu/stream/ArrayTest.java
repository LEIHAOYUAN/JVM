package com.stu.stream;

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
        error2();
    }



    private static void test1(){
        error1();
        correctForError1();
        correctForError2();
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
    private static void correctForError1(){
        Integer[] arr = {1,2,3};
        List list = Arrays.asList(arr);
        log.info("list：{},size：{}，class：{}",list,list.size(),list.get(0).getClass());
    }

    /**
     * 使用Arrays.stream方式解决
     */
    private static void correctForError2(){
        int[] arr = {1,2,3};
        List list = Arrays.stream(arr).boxed().collect(Collectors.toList());
        log.info("list：{},size：{}，class：{}",list,list.size(),list.get(0).getClass());
    }



    private static void error2(){
        String[] arr = {"1","2","3"};
        List list = Arrays.asList(arr);
        arr[1] = "4";
        list.add("5");
        log.info("list：{},size：{}，class：{}",list,list.size(),list.get(0).getClass());
    }






}
