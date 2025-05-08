package com.dxs.auth.core.usecase;

import com.dxs.auth.core.entity.AbstractUser;
import com.dxs.auth.core.exceptions.AuthenticationFailedException;
import com.dxs.auth.core.external.IPasswordEncrypt;
import com.dxs.auth.core.external.repository.AbstractUserRepository;
import com.dxs.auth.core.response.Response;
import com.dxs.auth.core.token.ITokenManager;

public class LoginUseCase<T extends AbstractUser> {
    private final AbstractUserRepository<T> userRepository;
    private final IPasswordEncrypt passwordEncrypt;
    private final ITokenManager tokenManager;
    public LoginUseCase(AbstractUserRepository<T> userRepository, IPasswordEncrypt passwordEncrypt, ITokenManager tokenManager) {
        this.userRepository = userRepository;
        this.passwordEncrypt = passwordEncrypt;
        this.tokenManager = tokenManager;
    }

    public Response<String> login(String email, String password) {
        T foundUser = userRepository.findByEmail(email)
                .filter(u -> passwordEncrypt.match(password, u.getPassword()))
                .orElseThrow(() -> new AuthenticationFailedException("Invalid Credentials"));

        String jwt = tokenManager.generateToken(foundUser.getId(), foundUser.getRole(), 900);
        return Response.success(jwt);
    }
}
