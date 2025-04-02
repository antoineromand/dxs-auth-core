package com.dxs.auth.core.usecase;

import com.dxs.auth.core.entity.AbstractUser;
import com.dxs.auth.core.exceptions.AuthenticationFailedException;
import com.dxs.auth.core.external.IPasswordEncrypt;
import com.dxs.auth.core.external.repository.AbstractUserRepository;
import com.dxs.auth.core.response.Response;

import java.util.Optional;

public class LoginUseCase<T extends AbstractUser> {
    private final AbstractUserRepository<T> userRepository;
    private final IPasswordEncrypt passwordEncrypt;
    public LoginUseCase(AbstractUserRepository<T> userRepository, IPasswordEncrypt passwordEncrypt) {
        this.userRepository = userRepository;
        this.passwordEncrypt = passwordEncrypt;
    }

    public Response<String> login(String email, String password) {
        Optional<T> user = this.userRepository.findByEmail(email);
        if (user.isEmpty() || !this.passwordEncrypt.match(user.get().getPassword(), password)) {
            throw new AuthenticationFailedException("Invalid Credentials");
        }
        // Generate JWT with User id and role
        // PlaceHolder
        String jwt = "XXX-FEFFEZF-FE";
        return Response.success(jwt);
    }
}
