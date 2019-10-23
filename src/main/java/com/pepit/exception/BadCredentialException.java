package com.pepit.exception;

public class BadCredentialException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String errCode;
    private String errMsg;

    public BadCredentialException(String message) {
        super("Unable to find user with id " +  message);
    }
}
