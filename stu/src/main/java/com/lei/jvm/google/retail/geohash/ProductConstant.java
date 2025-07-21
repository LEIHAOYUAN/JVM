package com.lei.jvm.google.retail.geohash;

/**
 * @author ryan
 */
public class ProductConstant {
    public static final Integer PRODUCT_BATCH_LIMIT = 100;
    public static final Integer GEO_HASH_BATCH_LIMIT = 3000;
    public static final Integer CUSTOM_ATTRIBUTE_TEXT_LEN_LIMIT = 256;
    public static final Integer CUSTOM_ATTRIBUTE_SIZE_LIMIT = 400;
    public static final Integer DESCRIPTION_MAX_LENGTH = 5000;

    public static final String PRODUCT_LOCAL_INVENTORY_AVAILABILITY = "availability";
    public static final String DELIVERY_BY_SELF = "self_delivery";
    public static final String DELIVERY_BY_GRUBHUB = "grubhub_delivery";
    public static final String DELIVERY_BY_COMBINE = "self_grubhub_delivery";
}
