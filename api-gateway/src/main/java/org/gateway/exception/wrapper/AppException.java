package org.gateway.exception.wrapper;

public class AppException extends RuntimeException{
    public AppException(String message)
    {
        super(message);
    }
}