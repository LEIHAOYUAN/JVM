package com.algorithm.other.card;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.List;

@Slf4j
public class CardSorter {

    public static void sortCards(List<Card> cards) {
        cards.sort(Comparator.comparing(Card::getSuit).thenComparingInt(c -> c.getRank().getValue()));
    }

    public static void main(String[] args) {
        List<Card> deck = Lists.newArrayList();
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                deck.add(new Card(suit, rank));
            }
        }
        log.info("排序前={}", JSON.toJSONString(deck));
        sortCards(deck);
        log.info("排序后={}", JSON.toJSONString(deck));
    }
}
