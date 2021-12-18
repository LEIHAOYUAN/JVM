package com.lei.stu.compare;

/**
 * Comparable:接口
 */
public class ComparableDemo implements Comparable<ComparableDemo> {
    private String str;

    public ComparableDemo(String str) {
        this.str = str;
    }

    @Override
    public int compareTo(ComparableDemo domain) {
        if (this.str.compareTo(domain.str) > 0)
            return 1;
        else if (this.str.compareTo(domain.str) == 0)
            return 0;
        else
            return -1;
    }

    public String getStr() {
        return str;
    }
}