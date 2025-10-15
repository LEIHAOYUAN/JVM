package com.lei.jvm.google.retail.geohash;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author ryan
 */
public class ProductConstant {
    public static ExecutorService MONITOR_EXECUTOR = Executors.newFixedThreadPool(10);
    public static final TimeUnit DEFAULT_TIMEOUT_UNIT = TimeUnit.MINUTES;
    public static final int DEFAULT_TIMEOUT_MINUTES = 5;

    public static final Integer PRODUCT_BATCH_LIMIT = 100;
    public static final Integer GEO_HASH_BATCH_LIMIT = 3000;
    public static final Integer CUSTOM_ATTRIBUTE_TEXT_LEN_LIMIT = 256;
    public static final Integer CUSTOM_ATTRIBUTE_SIZE_LIMIT = 400;
    public static final Integer DESCRIPTION_MAX_LENGTH = 5000;

    public static final String PRODUCT_LOCAL_INVENTORY_DISTANCE = "distance_in_miles";
    public static final String PRODUCT_RESTAURANT_BRAND_CATEGORY = "restaurant_brand_category";
}
