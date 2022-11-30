package com.example.bermuda.exception.exceptions;

public class CUserDiaryNotFoundException extends RuntimeException {

    public CUserDiaryNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }

    public CUserDiaryNotFoundException(String msg) {
        super(msg);
    }

    public CUserDiaryNotFoundException() {
        super();
    }
}