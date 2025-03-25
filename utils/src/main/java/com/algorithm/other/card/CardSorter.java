package com.algorithm.other.card;

import com.algorithm.enums.ColorEnum;
import com.algorithm.enums.ValueEnum;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.List;

@Slf4j
public class CardSorter {

    public static void main(String[] args) {
        List<Card> param = Lists.newArrayList(new Card(ColorEnum.BLUE, ValueEnum.A), new Card(ColorEnum.RED, ValueEnum.A));
        log.info("排序前={}", JSON.toJSONString(param));
        doSortCards(param);
        log.info("排序后={}", JSON.toJSONString(param));
    }

    private static void doSortCards(List<Card> cards) {
        // 使用显式的泛型类型 <Card> 来避免编译错误
        cards.sort(Comparator.<Card>comparingInt(card -> card.getColor().getValue()).thenComparingInt(card -> card.getValue().getValue()));
    }
}
