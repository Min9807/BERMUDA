package com.example.bermuda.exception.exceptions;

public class CStringFormatException extends RuntimeException {
    public CStringFormatException (String msg, Throwable t) {
        super(msg, t);
    }

    public CStringFormatException(String msg) {
        super(msg);
    }

    public CStringFormatException() {
        super();
    }
}
