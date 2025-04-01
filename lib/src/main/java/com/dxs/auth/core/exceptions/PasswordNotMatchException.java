package com.dxs.auth.core.exceptions;

public class PasswordNotMatchException extends RuntimeException {
    private String code;
    public PasswordNotMatchException(String message) {
        super(message);
        this.code = "password_incorrect";
    }
}
