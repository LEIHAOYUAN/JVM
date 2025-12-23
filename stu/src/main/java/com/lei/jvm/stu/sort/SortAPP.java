package com.lei.jvm.stu.sort;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author ryan
 */
@Slf4j
public class SortAPP {

    public static void main(String[] args) {
        doSort(mockData());
    }

    private static void doSort(String dataJson) {
        List<MatchedItemAJAXView> originalItems = JSON.parseArray(dataJson, MatchedItemAJAXView.class);
        originalItems.sort(new MatchedItemAJAXView());
        log.info("测试结果:{}", JSON.toJSON(originalItems.stream().map(i -> i.dishName).toList()));
    }

    private static String mockData() {
        return """
            [
                {"dish_name":"POPULAR_NO_STOCK_IDX0","is_popular_item":true,"has_stock":false,"is_global_item":false,"relevance_score":0.0,"index_in_popular_category":0},
                {"dish_name":"POPULAR_NO_STOCK_IDX1","is_popular_item":true,"has_stock":false,"is_global_item":false,"relevance_score":0.0,"index_in_popular_category":2},
                {"dish_name":"POPULAR_HAS_STOCK_IDX0","is_popular_item":true,"has_stock":true,"is_global_item":false,"relevance_score":0.0,"index_in_popular_category":3},
                {"dish_name":"NOT_POPULAR_NO_STOCK_IDX0","is_popular_item":false,"has_stock":false,"is_global_item":false,"relevance_score":0.0,"index_in_popular_category":6}
                {"dish_name":"NOT_POPULAR_HAS_STOCK_IDX0","is_popular_item":false,"has_stock":true,"is_global_item":false,"relevance_score":0.0,"index_in_popular_category":5}
            ]
            """;
    }

}
