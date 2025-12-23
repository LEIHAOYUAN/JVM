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
                {"dish_name":"NO_STOCK_idx0","is_popular_item":true,"index_in_popular_category":0,"has_stock":false,"is_global_item":false,"relevance_score":0.0},
                {"dish_name":"NO_STOCK_idx1","is_popular_item":true,"index_in_popular_category":1,"has_stock":false,"is_global_item":false,"relevance_score":0.0},
                {"dish_name":"HAS_STOCK_idx2","is_popular_item":true,"index_in_popular_category":2,"has_stock":true,"is_global_item":false,"relevance_score":0.0},
                {"dish_name":"HAS_STOCK_idx3","is_popular_item":true,"index_in_popular_category":3,"has_stock":true,"is_global_item":false,"relevance_score":0.0},
                {"dish_name":"NO_STOCK_idx4","is_popular_item":true,"index_in_popular_category":4,"has_stock":false,"is_global_item":false,"relevance_score":0.0}
            ]
            """;
    }

}
