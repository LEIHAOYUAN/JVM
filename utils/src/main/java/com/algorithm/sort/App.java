package com.algorithm.sort;

public class App {

    public static void main(String[] args) {

    }


    /**
     * 交换顺序
     * @param arr
     * @param i
     * @param j
     */
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
