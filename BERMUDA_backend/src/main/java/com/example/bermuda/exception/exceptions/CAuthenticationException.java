package com.example.bermuda.exception.exceptions;

public class CAuthenticationException extends RuntimeException{

    public CAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }

    public CAuthenticationException(String msg) {
        super(msg);
    }

    public CAuthenticationException(){
        super();
    }

}
