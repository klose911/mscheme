package org.klose.scheme.utils;

import org.klose.scheme.model.SFrame;
import org.klose.scheme.type.SObject;
import org.klose.scheme.type.SPrimitive;

import java.util.HashMap;
import java.util.Map;

import static org.klose.scheme.constant.SConstant.*;

public class InitEnv {
    private final static Map<String, SObject> builtinMap;

    static {
        builtinMap = new HashMap<>();

        builtinMap.put(NIL_SYMBOL, NIL);
        builtinMap.put(TRUE_SYMBOL, TRUE);
        builtinMap.put(FALSE_SYMBOL, FALSE);
        // number
        builtinMap.put(ADD, new SPrimitive("org.klose.scheme.primitive.AddFunc.add"));
        builtinMap.put(SUB, new SPrimitive("org.klose.scheme.primitive.SubtractFunc.subtract"));
        builtinMap.put(MUL, new SPrimitive("org.klose.scheme.primitive.MultipleFunc.multiple"));
        builtinMap.put(DIV, new SPrimitive("org.klose.scheme.primitive.DivideFunc.divide"));
        builtinMap.put(EQ, new SPrimitive("org.klose.scheme.primitive.CompareFunc.eq"));
        builtinMap.put(GT, new SPrimitive("org.klose.scheme.primitive.CompareFunc.greater"));
        builtinMap.put(LT, new SPrimitive("org.klose.scheme.primitive.CompareFunc.less"));
        // pair list
        builtinMap.put(CONS, new SPrimitive("org.klose.scheme.primitive.PairFunc.cons"));
        builtinMap.put(CAR, new SPrimitive("org.klose.scheme.primitive.PairFunc.car"));
        builtinMap.put(CDR, new SPrimitive("org.klose.scheme.primitive.PairFunc.cdr"));
        builtinMap.put(LIST, new SPrimitive("org.klose.scheme.primitive.ListFunc.list"));
        builtinMap.put(NULL, new SPrimitive("org.klose.scheme.primitive.ListFunc.isNull"));

        // bool
        builtinMap.put(AND, new SPrimitive("org.klose.scheme.primitive.BooleanFunc.and"));
        builtinMap.put(OR, new SPrimitive("org.klose.scheme.primitive.BooleanFunc.or"));
        builtinMap.put(NOT, new SPrimitive("org.klose.scheme.primitive.BooleanFunc.not"));
        builtinMap.put(EQUALS, new SPrimitive("org.klose.scheme.primitive.BooleanFunc.equalsTo"));

        //other
        builtinMap.put(APPLY, new SPrimitive("org.klose.scheme.primitive.ApplyFunc.apply"));
        builtinMap.put(PRINT, new SPrimitive("org.klose.scheme.primitive.PrintFunc.print"));
    }

    /**
     * initialize root environment with primitive functions
     * and literals(<code>true</>, <code>false</>, <code>nil</code>)
     *
     * @return initialized root environment
     */
    public static SFrame init() {
        return new SFrame(builtinMap, null);
    }
}
