package com.lei.jvm.utils.base.utils.pair;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.poi.ss.formula.functions.T;

import java.math.BigDecimal;
import java.util.AbstractMap;
import java.util.List;

/**
 *  职能描述：
 *  @author leihaoyuan
 *  @version 2023/1/12 13:26
 */
@Slf4j
@UtilityClass
public class PairUtil {

    public static void main(String[] args) {

        //java entry实现
        AbstractMap.SimpleEntry<Boolean, BigDecimal> entry = new AbstractMap.SimpleEntry<>(Boolean.TRUE, BigDecimal.ZERO);
        log.info(entry.getKey() + ":" + entry.getValue());

        //java entry实现：不可变对象
        AbstractMap.SimpleImmutableEntry<Boolean, BigDecimal> immutableEntry = new AbstractMap.SimpleImmutableEntry<>(Boolean.TRUE, BigDecimal.ZERO);
        log.info(immutableEntry.getKey() + ":" + immutableEntry.getValue());

        //pair实现，同ImmutablePair：不可变对象
        Pair pair = Pair.of(Boolean.TRUE, BigDecimal.ZERO);
        log.info(pair.getLeft() + ":" + pair.getRight());

        Pair<Boolean, BigDecimal> immutablePair = ImmutablePair.of(Boolean.TRUE, BigDecimal.ZERO);
        log.info(immutablePair.getLeft() + ":" + immutablePair.getRight());

        //可变pair实现： MutablePair
        MutablePair<Boolean, BigDecimal> mutablePair = new MutablePair();
        mutablePair.setLeft(Boolean.TRUE);
        mutablePair.setRight(BigDecimal.ZERO);
        log.info(mutablePair.getLeft() + ":" + mutablePair.getRight());


        new MutablePair<List<T>,T>();
    }


}
