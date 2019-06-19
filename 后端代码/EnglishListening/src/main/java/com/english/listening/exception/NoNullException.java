package com.english.listening.exception;

public class NoNullException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NoNullException(String message){
        super(message);
    }
    public NoNullException(Throwable cause){
        super(cause);
    }
    public NoNullException(String message, Throwable cause){
        super(message,cause);
    }
}
