package org.klose.scheme.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

public class Utils {
    private final static Logger logger = LoggerFactory.getLogger(Utils.class);

    /**
     * 判断是否为整数
     *
     * @param str 传入的字符串
     * @return 是整数返回true, 否则返回false
     **/
    public static boolean isLong(String str) {
        logger.debug("str = {}", str);
        boolean result = isMatch("^[-\\+]?[\\d]*$", str);
        logger.debug("isLong : {}", result);
        return result;
    }

    public static boolean isFloat(String str) {
        logger.debug("str = {}", str);
        boolean result = isMatch("[-+]{0,1}\\d+\\.\\d*|[-+]{0,1}\\d*\\.\\d+",
                str);
        logger.debug("isFloat : {}", result);
        return result;
    }

    private static boolean isMatch(String regStr, String str) {
        if (str == null || StringUtils.isBlank(str))
            return false;
        Pattern pattern = Pattern.compile(regStr);
        return pattern.matcher(str).matches();

    }
}
