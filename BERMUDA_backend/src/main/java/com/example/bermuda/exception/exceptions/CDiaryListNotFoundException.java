package com.example.bermuda.exception.exceptions;

public class CDiaryListNotFoundException extends RuntimeException{

    public CDiaryListNotFoundException() {
        super();
    }

    public CDiaryListNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }

    public CDiaryListNotFoundException(String msg) {
        super(msg);
    }

}