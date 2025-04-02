package com.dxs.auth.core.usecase;

import com.dxs.auth.core.entity.AbstractUser;
import com.dxs.auth.core.entity.IUserFactory;
import com.dxs.auth.core.exceptions.EmailAlreadyExistsException;
import com.dxs.auth.core.external.IPasswordEncrypt;
import com.dxs.auth.core.external.repository.AbstractUserRepository;
import com.dxs.auth.core.response.RegisterInput;
import com.dxs.auth.core.response.Response;

public class RegisterUseCase <T extends AbstractUser, I extends RegisterInput> {
    private final AbstractUserRepository<T> userRepository;
    private final IPasswordEncrypt passwordEncrypt;
    private final IUserFactory<T, I> userFactory;
    public RegisterUseCase(AbstractUserRepository<T> userRepository, IPasswordEncrypt passwordEncrypt, IUserFactory<T, I> userFactory) {
        this.userRepository = userRepository;
        this.passwordEncrypt = passwordEncrypt;
        this.userFactory = userFactory;
    }

    public Response<T> register(I registerInput) {
        if (userRepository.findByEmail(registerInput.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists: " + registerInput.getEmail());
        }
        String encryptPassword = this.passwordEncrypt.encrypt(registerInput.getPassword());
        T user = this.userFactory.createUser(registerInput,encryptPassword);
        T savedUser = this.userRepository.save(user);
        return Response.success(savedUser);
    }
}
