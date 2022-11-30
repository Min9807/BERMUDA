package com.example.bermuda.exception.exceptions;

public class CWrongDiaryIdException extends RuntimeException{

    public CWrongDiaryIdException(String msg, Throwable t) {
        super(msg, t);
    }

    public CWrongDiaryIdException(String msg) {
        super(msg);
    }

    public CWrongDiaryIdException(){
        super();
    }

}
