package com.dxs.auth.core.usecase;

import com.dxs.auth.core.entity.AbstractUser;
import com.dxs.auth.core.exceptions.AuthenticationFailedException;
import com.dxs.auth.core.external.IPasswordEncrypt;
import com.dxs.auth.core.external.TokenManager;
import com.dxs.auth.core.external.repository.AbstractUserRepository;
import com.dxs.auth.core.response.Response;

import java.util.Optional;

public class LoginUseCase<T extends AbstractUser> {
    private final AbstractUserRepository<T> userRepository;
    private final IPasswordEncrypt passwordEncrypt;
    private final TokenManager tokenManager;
    public LoginUseCase(AbstractUserRepository<T> userRepository, IPasswordEncrypt passwordEncrypt, TokenManager tokenManager) {
        this.userRepository = userRepository;
        this.passwordEncrypt = passwordEncrypt;
        this.tokenManager = tokenManager;
    }

    public Response<String> login(String email, String password) {
        T foundUser = userRepository.findByEmail(email)
                .filter(u -> passwordEncrypt.match(u.getPassword(), password))
                .orElseThrow(() -> new AuthenticationFailedException("Invalid Credentials"));

        String jwt = tokenManager.generateToken(foundUser.getId(), foundUser.getRole(), 900);
        return Response.success(jwt);
    }
}
