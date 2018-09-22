package org.klose.scheme.type;

import org.apache.commons.lang3.StringUtils;
import org.klose.scheme.model.SProcedure;

public class SPrimitive extends SProcedure {
    private final String value;

    public SPrimitive(String value) {
        super(null, null, null);

        if (StringUtils.isEmpty(value))
            throw new IllegalArgumentException("primitive function string can not be empty");
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    public String getClazz() {
        return value.substring(0, value.lastIndexOf("."));
    }

    public String getMethod() {
        return value.substring(value.lastIndexOf(".") + 1);
    }

    @Override
    public String toString() {
        return "Primitive{" +
                "value='" + value + '\'' +
                '}';
    }
}
