package org.klose.scheme.exception;

public class WrongArgumentTypeException extends Exception {

    private final Class expected;
    private final Class actual;
    private final String procedure;

    public WrongArgumentTypeException(Class expected, Class actual, String procedure) {
        this.expected = expected;
        this.actual = actual;
        this.procedure = procedure;
    }

    @Override
    public String getMessage() {
        return "procedure \"" + procedure +
                "\" : argument should be " + expected + "  " +
                "but actually " + actual;
    }

    public Class getExpected() {
        return expected;
    }

    public Class getActual() {
        return actual;
    }

    public String getProcedure() {
        return procedure;
    }
}
