package com.lei.jvm.stu.sort;

import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;

/**
 * @author danson
 */
public class MatchedItemAJAXView implements Comparator<MatchedItemAJAXView> {

    public String dishName;

    public Boolean isPopularItem;

    public Integer indexInPopularCategory;

    public Boolean hasStock;

    public Boolean isGlobalItem;

    public Double relevanceScore;

    @Override
    public int compare(MatchedItemAJAXView i1, MatchedItemAJAXView i2) {
        if (i1.isPopularItem) {
            if (Objects.equals(i1.hasStock, i2.hasStock)) {
                return i1.indexInPopularCategory.compareTo(i2.indexInPopularCategory);
            } else {
                return Optional.ofNullable(i2.hasStock).orElse(Boolean.FALSE).compareTo(Optional.ofNullable(i1.hasStock).orElse(Boolean.FALSE));
            }
        }
        if (Objects.equals(i1.hasStock, i2.hasStock)) {
            if (Objects.equals(i1.isGlobalItem, i2.isGlobalItem)) {
                return Optional.ofNullable(i2.relevanceScore).orElse(0D).compareTo(Optional.ofNullable(i1.relevanceScore).orElse(0D));
            } else {
                return Optional.ofNullable(i1.isGlobalItem).orElse(Boolean.FALSE).compareTo(Optional.ofNullable(i2.isGlobalItem).orElse(Boolean.FALSE));
            }
        } else {
            return Optional.ofNullable(i2.hasStock).orElse(Boolean.FALSE).compareTo(Optional.ofNullable(i1.hasStock).orElse(Boolean.FALSE));
        }
    }
}
