package org.klose.scheme.utils;

import org.apache.commons.lang3.math.NumberUtils;

public class EvalUtils {

    public static boolean isString(String str) {
        return str.startsWith("\"") && str.endsWith("\"");
    }

    public static boolean isNumber(String str) {
        return NumberUtils.isNumber(str);
    }
}
