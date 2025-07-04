package com.lei.jvm.google.retail.build;

import com.google.cloud.retail.v2.BranchName;
import com.google.cloud.retail.v2beta.ProductName;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * location官网：https://cloud.google.com/vertex-ai/docs/general/locations?hl=zh-cn
 */
public class CommonBuilder {

    public static String buildBranch() {
        return BranchName.of("wonder-ai-search-dev", "global", "default_catalog", "2").toString();
    }

    public static String buildProduct(String productId) {
        return ProductName.of("wonder-ai-search-dev", "global", "default_catalog", "2", productId).toString();
    }

    public static List<String> buildCatagoryList() {
        return Lists.newArrayList("Custom", "Poke Bowls", "Sides", "Kids", "Beverages", "Desserts");
    }

    public static String buildINFilter(List<String> productIds) {
        return "type = \"COLLECTION\" AND primary_product_id : ANY (\"" + String.join("\",\"", productIds) + "\")";
    }

    public static String buildCollectionIdFilter(List<String> productIds) {
        return productIds.stream()
                .map(id -> "productId = \"" + id + "\"")
                .reduce((a, b) -> a + " or " + b)
                .orElse("");
    }

    public static String buildPrimaryIdFilter(List<String> productIds) {
        return "primary_product_id IN (\"" + String.join("\",\"", productIds) + "\")";
    }
}
