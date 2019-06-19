package com.english.listening.exception;

public class NoUnllException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NoUnllException(String message){
        super(message);
    }
    public NoUnllException(Throwable cause){
        super(cause);
    }
    public NoUnllException(String message,Throwable cause){
        super(message,cause);
    }
}
