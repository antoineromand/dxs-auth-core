package com.dxs.auth.core.service;

import com.dxs.auth.core.entity.AbstractUser;
import com.dxs.auth.core.exceptions.AuthenticationFailedException;
import com.dxs.auth.core.repository.AbstractUserRepository;

public class AuthenticatorService<T extends AbstractUser> {
    private final AbstractUserRepository<T> userRepository;
    private final IPasswordEncryptService passwordEncrypt;

    public AuthenticatorService(AbstractUserRepository<T> userRepository, IPasswordEncryptService passwordEncrypt) {
        this.userRepository = userRepository;
        this.passwordEncrypt = passwordEncrypt;
    }

    public T authenticate(String email, String password) {
        return userRepository.findByEmail(email)
                .filter(u -> passwordEncrypt.match(password, u.getPassword()))
                .orElseThrow(() -> new AuthenticationFailedException("Invalid Credentials"));
    }
}
