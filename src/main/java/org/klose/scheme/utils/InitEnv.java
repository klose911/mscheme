package org.klose.scheme.utils;

import org.klose.scheme.model.SEnvironment;
import org.klose.scheme.type.SPrimitive;
import org.klose.scheme.type.SObject;

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
        builtinMap.put(EQUALS, new SPrimitive("org.klose.scheme.primitive.BooleanFunc.eq"));
    }

    public static SEnvironment init() {
        return new SEnvironment(builtinMap, null);
    }
}
