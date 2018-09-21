package org.klose.scheme.utils;

import org.klose.scheme.model.SEnvironment;
import org.klose.scheme.type.SFunc;
import org.klose.scheme.type.SObject;

import java.util.HashMap;
import java.util.Map;

import static org.klose.scheme.constant.SConstant.*;

public class InitEnv {
    private static Map<String, SObject> builtinMap;

    static {
        builtinMap = new HashMap<>();

        builtinMap.put(NIL_SYMBOL, NIL);
        builtinMap.put(TRUE_SYMBOL, TRUE);
        builtinMap.put(FALSE_SYMBOL, FALSE);
        // number
        builtinMap.put(ADD, new SFunc("org.klose.scheme.builtin.AddFunc.add"));
        builtinMap.put(SUB, new SFunc("org.klose.scheme.builtin.SubtractFunc.subtract"));
        builtinMap.put(MUL, new SFunc("org.klose.scheme.builtin.MultipleFunc.multiple"));
        builtinMap.put(DIV, new SFunc("org.klose.scheme.builtin.DivideFunc.divide"));
        builtinMap.put(EQ, new SFunc("org.klose.scheme.builtin.CompareFunc.eq"));
        builtinMap.put(GT, new SFunc("org.klose.scheme.builtin.CompareFunc.greater"));
        builtinMap.put(LT, new SFunc("org.klose.scheme.builtin.CompareFunc.less"));
        // pair list
        builtinMap.put(CONS, new SFunc("org.klose.scheme.builtin.PairFunc.cons"));
        builtinMap.put(CAR, new SFunc("org.klose.scheme.builtin.PairFunc.car"));
        builtinMap.put(CDR, new SFunc("org.klose.scheme.builtin.PairFunc.cdr"));
        builtinMap.put(LIST, new SFunc("org.klose.scheme.builtin.ListFunc.list"));
        builtinMap.put(NULL, new SFunc("org.klose.scheme.builtin.ListFunc.isNull"));

        // bool
        builtinMap.put(AND, new SFunc("org.klose.scheme.builtin.BooleanFunc.and"));
        builtinMap.put(OR, new SFunc("org.klose.scheme.builtin.BooleanFunc.or"));
        builtinMap.put(NOT, new SFunc("org.klose.scheme.builtin.BooleanFunc.not"));
        builtinMap.put(EQUALS, new SFunc("org.klose.scheme.builtin.BooleanFunc.eq"));
    }

    public static SEnvironment init() {
        return new SEnvironment(builtinMap, null);
    }
}
