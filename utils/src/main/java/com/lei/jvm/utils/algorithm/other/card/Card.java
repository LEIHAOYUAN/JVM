package com.lei.jvm.utils.algorithm.other.card;

import com.lei.jvm.utils.algorithm.enums.ColorEnum;
import com.lei.jvm.utils.algorithm.enums.ValueEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author leihaoyuan
 * @date 2025年03月25日 10:02
 * @description
 */
@Getter
@AllArgsConstructor
public class Card {

    private ColorEnum color;

    private ValueEnum value;
}
