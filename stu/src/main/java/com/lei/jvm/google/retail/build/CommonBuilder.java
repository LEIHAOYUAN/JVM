package com.lei.jvm.google.retail.build;

import com.google.cloud.retail.v2.BranchName;
import com.google.cloud.retail.v2beta.ProductName;
import com.google.common.collect.Lists;
import com.google.protobuf.Timestamp;

import java.time.Instant;
import java.util.List;

/**
 * location官网：https://cloud.google.com/vertex-ai/docs/general/locations?hl=zh-cn
 */
public class CommonBuilder {

    private static final String SEARCH_PROJECT_ID = "wonder-ai-search-dev";
    private static final String REC_PROJECT_ID = "wonder-search-restaurant-dev";
    private static final String DANSON_TEST = "danson-test";

    private static final String CURRENT_PROJECT_ID = REC_PROJECT_ID;


    private static final String DEFAULT_BRANCH = "2";


    public static String buildBranch() {
        return BranchName.of(CURRENT_PROJECT_ID, "global", "default_catalog", DEFAULT_BRANCH).toString();
    }

    public static String buildPlacement() {
        return "projects/" + CURRENT_PROJECT_ID + "/locations/global/catalogs/default_catalog/placements/default_search";
    }

    public static String buildProduct(String productId) {
        return ProductName.of(CURRENT_PROJECT_ID, "global", "default_catalog", DEFAULT_BRANCH, productId).toString();
    }

    public static Timestamp buildUTCTimestamp() {
        Instant now = Instant.now();
        return Timestamp.newBuilder()
            .setSeconds(now.getEpochSecond())
            .setNanos(now.getNano())
            .build();
    }

    public static List<String> buildCatagoryList() {
        return Lists.newArrayList("Custom", "Poke Bowls", "Sides", "Kids", "Beverages", "Desserts");
    }

}
