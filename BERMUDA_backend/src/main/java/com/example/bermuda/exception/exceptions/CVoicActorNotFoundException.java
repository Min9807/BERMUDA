package com.example.bermuda.exception.exceptions;

public class CVoicActorNotFoundException extends RuntimeException {

    public CVoicActorNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }

    public CVoicActorNotFoundException(String msg) {
        super(msg);
    }

    public CVoicActorNotFoundException() {
        super();
    }
}