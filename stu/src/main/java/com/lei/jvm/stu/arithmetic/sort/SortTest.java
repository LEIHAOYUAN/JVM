package com.lei.jvm.stu.arithmetic.sort;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author leihaoyuan
 * @Date 2022/6/7 19:34
 * @Version 1.0
 * @Description
 */
@Slf4j
public class SortTest {

    public static void main(String[] args) {
        int[] arr = {1, 5, 3, 6, 0};
        log.info("冒泡排序:{}", JSON.toJSONString(bubbleSort(arr)));
        log.info("选择排序:{}", JSON.toJSONString(selectSort(arr)));
    }

    public static int[] bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    public static int[] selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }


}
