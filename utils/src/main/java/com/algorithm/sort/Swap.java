package com.algorithm.sort;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Swap {

    public static void main(String[] args) {
        // 使用异或交换两个数值
        swap(88, 99);
    }


    public static void swap(int a, int b) {
        log.info("交换前：a={}，b={}", a, b);
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        log.info("交换后：a={}，b={}", a, b);
    }

    /**
     * 交换顺序
     *
     * @param arr
     * @param i
     * @param j
     */
    public static void swap01(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void swap02(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

}
