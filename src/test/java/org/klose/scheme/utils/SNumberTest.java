package org.klose.scheme.utils;


import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;

public class SNumberTest {

    @Test
    public void createNumber() {
        assertTrue(NumberUtils.createNumber("-222d") instanceof Double);
        assertTrue(NumberUtils.createNumber("1000d") instanceof Double);
        assertTrue(NumberUtils.createNumber("-98.231") instanceof Float);
        assertTrue(NumberUtils.createNumber("123.233f") instanceof Float);
        assertTrue(NumberUtils.createNumber("1000L") instanceof Long);
        assertTrue(NumberUtils.createNumber("1000") instanceof Integer);
    }
}
