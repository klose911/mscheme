package org.klose.scheme.utils;

import org.apache.commons.lang3.math.NumberUtils;

public class EvalUtils {

    /**
     * Checks whether the Input is a valid Java String literal
     *
     * @param str the Input to check
     * @return true if the Input is a valid Java String literal
     */
    public static boolean isString(String str) {
        if (str == null)
            throw new IllegalArgumentException("isString str can not null");

        return str.startsWith("\"") && str.endsWith("\"");
    }

    /**
     * Checks whether the String a valid number
     *
     * @param str the String to check
     * @return true if the string is a correctly formatted number
     */
    public static boolean isNumber(String str) {
        return NumberUtils.isNumber(str);
    }

    private EvalUtils() {
        throw new UnsupportedOperationException("illegal constructor for EvalUtils");
    }
}
