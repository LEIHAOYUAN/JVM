package com.algorithm.other.max;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Max {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 788, 8, 9, 10};
        log.info("获取数组最大值={}", max(arr, 0, arr.length - 1));
    }


    public static int max(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }
        int mid = L + ((R - L) >> 1);
        int leftMax = max(arr, L, mid);
        int rightMax = max(arr, mid + 1, R);
        return Math.max(leftMax, rightMax);
    }


}
