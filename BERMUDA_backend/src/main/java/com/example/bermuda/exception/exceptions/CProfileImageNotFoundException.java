package com.example.bermuda.exception.exceptions;

public class CProfileImageNotFoundException extends RuntimeException {

    public CProfileImageNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }

    public CProfileImageNotFoundException(String msg) {
        super(msg);
    }

    public CProfileImageNotFoundException() {
        super();
    }
}