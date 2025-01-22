package com.algorithm.search;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Search {

    public static void main(String[] args) {
        log.info("二分法找目标数值={}", binarySearch(new int[]{1, 2, 3, 4, 5}, 5));
    }

    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            // 计算数组中间位置的索引
            int mid = left + (right - left) / 2;
            // 检查中间位置是否为target
            if (arr[mid] == target) {
                return mid;
            }
            // 如果目标值大于中间值，则在右半部分继续查找
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // 如果没有找到目标值，返回 -1
        return -1;
    }

}
