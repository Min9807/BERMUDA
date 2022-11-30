package com.example.bermuda.exception.exceptions;

public class CNoRelationException extends RuntimeException{

    public CNoRelationException(String msg, Throwable t) {
        super(msg, t);
    }

    public CNoRelationException(String msg) {
        super(msg);
    }

    public CNoRelationException(){
        super();
    }

}
