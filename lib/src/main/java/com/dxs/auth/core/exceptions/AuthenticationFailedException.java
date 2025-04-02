package com.dxs.auth.core.exceptions;

public class AuthenticationFailedException extends RuntimeException{
    private String code;
    public AuthenticationFailedException(String message) {
        super(message);
        this.code = "authentication_failed";
    }
}
