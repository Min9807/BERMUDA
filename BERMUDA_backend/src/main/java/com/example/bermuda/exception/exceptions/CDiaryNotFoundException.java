package com.example.bermuda.exception.exceptions;

public class CDiaryNotFoundException extends RuntimeException{

    public CDiaryNotFoundException() {
        super();
    }

    public CDiaryNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }

    public CDiaryNotFoundException(String msg) {
        super(msg);
    }

}