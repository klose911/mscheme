package org.klose.scheme.constant;

import org.klose.scheme.type.SBoolean;
import org.klose.scheme.type.SList;

public class SConstant {

    public final static SList NIL = new SList(null, null);
    public final static SBoolean TRUE = new SBoolean(true);
    public final static SBoolean FALSE = new SBoolean(false);

    // bool functions
    public static final String AND = "and";
    public static final String OR = "or";
    public static final String NOT = "not";

    // number functions
    public static final String ADD = "+";
    public static final String SUB = "-";
    public static final String MUL = "*";
    public static final String DIV = "/";
    public static final String GT = ">";
    public static final String LT = "<";
    public static final String EQ = "=";
    // pair functions
    public static final String CONS = "cons";
    public static final String CAR = "car";
    public static final String CDR = "cdr";
    public static final String LIST = "list";
    public static final String NULL = "null?";
    // string functions
    public static final String EQUALS = "equals";
    //other func
    public static final String APPLY = "apply";
    public static final String PRINT = "print";

    // primitive literals
    public static final String NIL_SYMBOL = "nil";
    public static final String TRUE_SYMBOL = "true";
    public static final String FALSE_SYMBOL = "false";

    private SConstant() {
        throw new UnsupportedOperationException("illegal constructor for SConstant");
    }
}
