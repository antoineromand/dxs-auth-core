package com.dxs.auth.core.exceptions;

public class EmailAlreadyExistsException extends RuntimeException {
    private String code;
    public EmailAlreadyExistsException(String message) {
        super(message);
        this.code = "email_already_exists";
    }
}
