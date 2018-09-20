package org.klose.scheme.model;

import junit.framework.Assert;
import org.junit.Test;
import org.klose.scheme.type.SNumber;

public class SEnviormentTest {

    @Test
    public void defineVariable() {
        SEnvironment env = new SEnvironment();
        env.define("x", new SNumber(100));
        Assert.assertEquals(100, ((SNumber) env.lookup("x")).getValue());
    }

    @Test
    public void defineVariable2() {
        SEnvironment env1 = new SEnvironment();
        env1.define("x", new SNumber(100));
        SEnvironment env2 = new SEnvironment();
        env2.setParent(env1);
        env2.define("x", new SNumber(200));
        Assert.assertEquals(100,
                ((SNumber) env1.lookup("x")).getValue());
        Assert.assertEquals(200,
                ((SNumber) env2.lookup("x")).getValue());
    }


    @Test
    public void assignVariable() {
        SEnvironment env = new SEnvironment();
        env.define("x", new SNumber(100));
        env.assign("x", new SNumber(200));
        Assert.assertEquals(200,
                ((SNumber) env.lookup("x")).getValue());
    }

    @Test
    public void assignVariableError() {
        SEnvironment env = new SEnvironment();
        try {
            env.assign("x", new SNumber(200));
        } catch (Exception e) {
            Assert.assertEquals("not found assigned variable", e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void assignVariable2() {
        SEnvironment env1 = new SEnvironment();
        env1.define("x", new SNumber(100));
        SEnvironment env2 = new SEnvironment();
        env2.setParent(env1);
        env2.define("x", new SNumber(200));
        env1.assign("x", new SNumber(300));


        Assert.assertEquals(300,
                ((SNumber) env1.lookup("x")).getValue());
        Assert.assertEquals(200,
                ((SNumber) env2.lookup("x")).getValue());

    }
}
