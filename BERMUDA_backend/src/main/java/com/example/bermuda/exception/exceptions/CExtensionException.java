package com.example.bermuda.exception.exceptions;

public class CExtensionException extends RuntimeException {

    public CExtensionException(String msg, Throwable t) {
        super(msg, t);
    }

    public CExtensionException(String msg) {
        super(msg);
    }

    public CExtensionException() {
        super();
    }
}