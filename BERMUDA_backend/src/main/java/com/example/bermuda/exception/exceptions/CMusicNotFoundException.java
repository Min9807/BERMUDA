package com.example.bermuda.exception.exceptions;

public class CMusicNotFoundException extends RuntimeException {

    public CMusicNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }

    public CMusicNotFoundException(String msg) {
        super(msg);
    }

    public CMusicNotFoundException() {
        super();
    }
}