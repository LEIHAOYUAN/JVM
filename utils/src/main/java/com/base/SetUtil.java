package com.base;

import java.util.Set;

/**
 * @author ryan
 */
public class SetUtil {
    public static boolean isEmpty(Set<?> set) {
        return set == null || set.isEmpty();
    }

    public static boolean isNotEmpty(Set<?> set) {
        return !isEmpty(set);
    }
}
