package com.algorithm.sort;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author leihaoyuan
 * @Date 2022/6/7 19:34
 * @Version 1.0
 * @Description
 */
@Slf4j
public class Sort {

    public static void main(String[] args) {
        int[] arr = new int[]{8, 6, 5, 3, 1, 0};
        bubbleSort(new int[]{8, 6, 5, 3, 1, 0});
        selectionSort(new int[]{8, 6, 5, 3, 1, 0});
        insertionSort(new int[]{8, 6, 5, 3, 1, 0});
    }

    /**
     * 冒泡排序
     *
     * @param arr
     * @return
     */
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // 交换 arr[j] 和 arr[j+1]
                    swap(arr, j, j + 1);
                }
            }
        }
        log.info("冒泡排序:{}", JSON.toJSONString(arr));
    }

    /**
     * 选择排序
     *
     * @param arr
     * @return
     */
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // 交换 arr[i] 和 arr[minIndex]
            swap(arr, i, minIndex);
        }
        log.info("选择排序:{}", JSON.toJSONString(arr));
    }

    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
        log.info("插入排序:{}", JSON.toJSONString(arr));
    }


    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


}
