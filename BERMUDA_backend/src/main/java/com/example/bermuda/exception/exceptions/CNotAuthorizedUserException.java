package com.example.bermuda.exception.exceptions;

public class CNotAuthorizedUserException extends RuntimeException{

    public CNotAuthorizedUserException(String msg, Throwable t) {
        super(msg, t);
    }

    public CNotAuthorizedUserException(String msg) {
        super(msg);
    }

    public CNotAuthorizedUserException(){
        super();
    }

}
