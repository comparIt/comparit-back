package com.pepit.exception;

public class BusinessException extends Exception {

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException() {
        super();
    }

}
