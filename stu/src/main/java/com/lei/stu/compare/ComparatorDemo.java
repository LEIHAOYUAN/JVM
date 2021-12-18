package com.lei.stu.compare;

import java.util.Comparator;

/**
 * Comparator
 */
public class ComparatorDemo implements Comparator<ComparableDemo> {

    @Override
    public int compare(ComparableDemo domain1, ComparableDemo domain2) {
        if (domain1.getStr().compareTo(domain2.getStr()) > 0)
            return 1;
        else if (domain1.getStr().compareTo(domain2.getStr()) == 0)
            return 0;
        else
            return -1;
    }
}