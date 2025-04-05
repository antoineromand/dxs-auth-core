package com.dxs.auth.core.external;

public interface IPasswordEncrypt {
    String encrypt(String rawPassword);
    boolean match(String rawPassword, String encryptedPassword);
}
