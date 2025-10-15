package com.lei.jvm.google.retail.builder;

import java.util.Map;

public class GeoHashMapGenerator {
    private static final int KEY_LENGTH = 7;
    private static final int MAP_SIZE = 3000;
    private static final String LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final java.util.Set<String> globalKeys = new java.util.HashSet<>();

    public static Map<String, Double> generateUniqueMap() {
        Map<String, Double> result = new java.util.HashMap<>();
        java.util.Random random = new java.util.Random();
        while (result.size() < MAP_SIZE) {
            StringBuilder sb = new StringBuilder(KEY_LENGTH);
            for (int i = 0; i < KEY_LENGTH; i++) {
                sb.append(LETTERS.charAt(random.nextInt(LETTERS.length())));
            }
            String key = sb.toString();
            if (!globalKeys.contains(key)) {
                globalKeys.add(key);
                result.put(key, random.nextDouble());
            }
        }
        return result;
    }
}