package com.algorithm.other.card;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

@Slf4j
public class CardSorter {

    public static void main(String[] args) {
        List<Card> deck = Lists.newArrayList();
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                deck.add(new Card(suit, rank));
            }
        }
        log.info("排序前={}", JSON.toJSONString(deck));
        doSortCards(deck);
        log.info("排序后={}", JSON.toJSONString(deck));
    }

    private static void doSortCards(List<Card> cards) {
        cards.sort(Comparator.comparing(Card::getSuit).thenComparingInt(c -> c.getRank().getValue()));
    }


    @AllArgsConstructor
    public static class Card implements Serializable {

        public enum Suit {
            HEARTS, DIAMONDS, CLUBS, SPADES
        }


        public enum Rank {
            TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13), ACE(14);

            @Getter
            private final int value;

            Rank(int value) {
                this.value = value;
            }

            public int getValue() {
                return value;
            }
        }

        @Getter
        private final Suit suit;
        @Getter
        private final Rank rank;

        @Override
        public String toString() {
            return rank + " of " + suit;
        }
    }
}
