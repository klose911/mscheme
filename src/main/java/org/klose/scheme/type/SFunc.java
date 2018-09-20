package org.klose.scheme.type;

import org.apache.commons.lang3.StringUtils;
import org.klose.scheme.model.SProcedure;

public class SFunc extends SProcedure {
    public SFunc(String str) {
        if (StringUtils.isEmpty(str))
            throw new IllegalArgumentException("primitive function string can not be empty");
        this.str = str;
    }

    public String getClazz() {
        return str.substring(0, str.lastIndexOf("."));
    }

    public String getMethod() {
        return str.substring(str.lastIndexOf(".") + 1);
    }
}
