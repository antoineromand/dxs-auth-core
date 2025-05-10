package com.dxs.auth.core.service;

public interface IPasswordEncryptService {
    String encrypt(String rawPassword);
    boolean match(String rawPassword, String encryptedPassword);
}
