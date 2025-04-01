package com.dxs.auth.core.usecase;

import com.dxs.auth.core.entity.AbstractUser;
import com.dxs.auth.core.exceptions.PasswordNotMatchException;
import com.dxs.auth.core.exceptions.UserNotFoundException;
import com.dxs.auth.core.external.IPasswordEncrypt;
import com.dxs.auth.core.external.repository.UserRepository;
import com.dxs.auth.core.response.Response;

import java.util.Optional;

public class LoginUseCase<T extends AbstractUser> {
    private final UserRepository<T> userRepository;
    private final IPasswordEncrypt passwordEncrypt;
    public LoginUseCase(UserRepository<T> userRepository, IPasswordEncrypt passwordEncrypt) {
        this.userRepository = userRepository;
        this.passwordEncrypt = passwordEncrypt;
    }

    public Response<String> login(String email, String password) {
        Optional<T> user = this.userRepository.findUserByEmail(email);
        if (user.isEmpty()) {
            throw new UserNotFoundException("Cannot find user with the email : " + email);
        }
        if (!this.passwordEncrypt.match(user.get().getPassword(), password)) {
            throw new PasswordNotMatchException("Cannot match password from request with the user password's");
        }
        String jwt = "XXX-FEFFEZF-FE";
        return new Response<>(true, jwt);
    }
}
