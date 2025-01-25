package com.algorithm.other.uuid;

import java.util.Random;

public class UniqueNumberGenerator {



    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(generateUniqueNumber());
        }
    }


    private static final Random RANDOM = new Random();
    public static String generateUniqueNumber() {
        // 获取当前时间戳的毫秒数
        long timestamp = System.currentTimeMillis();
        // 取时间戳的最后6位
        long timestampPart = timestamp % 1000000;
        // 生成2位随机数
        int randomPart = RANDOM.nextInt(100);
        // 组合成8位数
        return String.format("%06d%02d", timestampPart, randomPart);
    }


}