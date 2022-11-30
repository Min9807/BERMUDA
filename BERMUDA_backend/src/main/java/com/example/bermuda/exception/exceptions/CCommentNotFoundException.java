package com.example.bermuda.exception.exceptions;

public class CCommentNotFoundException extends RuntimeException{
    public CCommentNotFoundException() {
        super();
    }

    public CCommentNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }

    public CCommentNotFoundException(String msg) {
        super(msg);
    }
}

