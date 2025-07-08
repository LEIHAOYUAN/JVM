package com.lei.jvm.google.retail.build;

import com.google.cloud.retail.v2.BranchName;
import com.google.cloud.retail.v2beta.ProductName;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * location官网：https://cloud.google.com/vertex-ai/docs/general/locations?hl=zh-cn
 */
public class CommonBuilder {

    private static final String SEARCH_PROJECT_ID = "wonder-ai-search-dev";
    private static final String REC_PROJECT_ID = "wonder-search-restaurant-dev";


    public static String buildSearchBranch() {
        return BranchName.of(SEARCH_PROJECT_ID, "global", "default_catalog", "2").toString();
    }

    public static String buildSearchPlacement() {
        return "projects/" + SEARCH_PROJECT_ID + "/locations/global/catalogs/default_catalog/placements/default_search";
    }

    public static String buildSearchProduct(String productId) {
        return ProductName.of(SEARCH_PROJECT_ID, "global", "default_catalog", "2", productId).toString();
    }

    public static String buildRecBranch() {
        return BranchName.of(REC_PROJECT_ID, "global", "default_catalog", "2").toString();
    }

    public static String buildRecProduct(String productId) {
        return ProductName.of(REC_PROJECT_ID, "global", "default_catalog", "2", productId).toString();
    }

    public static String buildRecPlacement() {
        return "projects/" + REC_PROJECT_ID + "/locations/global/catalogs/default_catalog/placements/default_search";
    }

    public static List<String> buildCatagoryList() {
        return Lists.newArrayList("Custom", "Poke Bowls", "Sides", "Kids", "Beverages", "Desserts");
    }

    public static String buildCollectionIdFilter(List<String> productIds) {
        return productIds.stream()
                .map(id -> "productId = \"" + id + "\"")
                .reduce((a, b) -> a + " or " + b)
                .orElse("");
    }

}
