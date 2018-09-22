package org.klose.scheme.model;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.klose.scheme.type.SNumber;

import java.util.HashMap;

import static junit.framework.Assert.assertEquals;

public class SFrameTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void defineVariable() {
        SFrame env = new SFrame(new HashMap<>(), null);
        env.define("x", new SNumber(100));
        assertEquals(100, env.lookup("x").getValue());
    }

    @Test
    public void defineVariableInOutSideEnv() {
        SFrame env1 = new SFrame(new HashMap<>(), null);
        env1.define("x", new SNumber(100));
        SFrame env2 = new SFrame(new HashMap<>(), env1);
        env2.define("x", new SNumber(200));
        assertEquals(100,
                env1.lookup("x").getValue());
        assertEquals(200,
                env2.lookup("x").getValue());
    }


    @Test
    public void assignVariable() {
        SFrame env = new SFrame(new HashMap<>(), null);
        env.define("x", new SNumber(100));
        env.assign("x", new SNumber(200));
        assertEquals(200,
                env.lookup("x").getValue());
    }

    @Test
    public void assignVariableError() {
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("not found assigned variable");
        SFrame env = new SFrame(new HashMap<>(), null);
        env.assign("x", new SNumber(200));
    }

    @Test
    public void assignVariableInOutSideEnv() {
        SFrame env1 = new SFrame(new HashMap<>(), null);
        env1.define("x", new SNumber(100));
        SFrame env2 = new SFrame(new HashMap<>(), null);
        env2.define("x", new SNumber(200));
        env1.assign("x", new SNumber(300));

        assertEquals(300,
                env1.lookup("x").getValue());
        assertEquals(200,
                env2.lookup("x").getValue());

    }
}
