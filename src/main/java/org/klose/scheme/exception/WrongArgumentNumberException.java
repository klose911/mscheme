package org.klose.scheme.exception;

public class WrongArgumentNumberException extends Exception {

    private final int expected;
    private final int actual;
    private final String procedure;

    public WrongArgumentNumberException(int expected, int actual, String procedure) {
        this.expected = expected;
        this.actual = actual;
        this.procedure = procedure;
    }

    @Override
    public String getMessage() {
        return "procedure \"" + procedure +
                "\" : should call by at least " + expected + " arguments, " +
                "but actually received " + actual + " arguments";
    }

    public int getExpected() {
        return expected;
    }

    public int getActual() {
        return actual;
    }

    public String getProcedure() {
        return procedure;
    }
}
