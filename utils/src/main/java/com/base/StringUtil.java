package com.base;

import java.util.function.Predicate;

/**
 * @author ryan
 */
public class StringUtil {
    public static boolean isNotBlank(CharSequence str) {
        return !isBlank(str);
    }

    public static boolean isBlank(CharSequence str) {
        if (str == null || str.length() == 0) {
            return true;
        }
        for (int i = 0; i < str.length(); ++i) {
            if (!isBlankChar(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static String trim(CharSequence str) {
        return str == null ? null : trim(str, 0, StringUtil::isBlankChar);
    }

    private static String trim(CharSequence str, int mode, Predicate<Character> predicate) {
        String result;
        if (str == null) {
            result = null;
        } else {
            int length = str.length();
            int start = 0;
            int end = length;
            if (mode <= 0) {
                while (start < end && predicate.test(str.charAt(start))) {
                    ++start;
                }
            }
            if (mode >= 0) {
                while (start < end && predicate.test(str.charAt(end - 1))) {
                    --end;
                }
            }
            if (start <= 0 && end >= length) {
                result = str.toString();
            } else {
                result = str.toString().substring(start, end);
            }
        }
        return result;
    }

    private static boolean isBlankChar(char c) {
        return isBlankChar((int) c);
    }

    private static boolean isBlankChar(int c) {
        return c == 0 || c == 65279 || c == 8234 || Character.isWhitespace(c) || Character.isSpaceChar(c);
    }
}
