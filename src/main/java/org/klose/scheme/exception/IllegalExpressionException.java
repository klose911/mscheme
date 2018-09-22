package org.klose.scheme.exception;

public class IllegalExpressionException extends Exception {

    public IllegalExpressionException(String message) {
        super(message);
    }

    public IllegalExpressionException(Throwable cause) {
        super(cause);
    }
}
