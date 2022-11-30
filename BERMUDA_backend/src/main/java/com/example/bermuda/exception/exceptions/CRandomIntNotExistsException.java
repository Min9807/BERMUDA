package com.example.bermuda.exception.exceptions;

public class CRandomIntNotExistsException extends RuntimeException{

    public CRandomIntNotExistsException(String msg, Throwable t) {
        super(msg, t);
    }

    public CRandomIntNotExistsException(String msg) {
        super(msg);
    }

    public CRandomIntNotExistsException() {
        super();
    }
}
