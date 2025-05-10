package com.dxs.auth.core.usecase;

import com.dxs.auth.core.entity.AbstractUser;
import com.dxs.auth.core.entity.IUserFactory;
import com.dxs.auth.core.exceptions.EmailAlreadyExistsException;
import com.dxs.auth.core.input.RegisterInput;
import com.dxs.auth.core.output.Response;
import com.dxs.auth.core.repository.AbstractUserRepository;
import com.dxs.auth.core.service.IPasswordEncryptService;

public class RegisterUseCase <T extends AbstractUser, I extends RegisterInput> {
    private final AbstractUserRepository<T> userRepository;
    private final IPasswordEncryptService passwordEncrypt;
    private final IUserFactory<T, I> userFactory;
    public RegisterUseCase(AbstractUserRepository<T> userRepository, IPasswordEncryptService passwordEncrypt, IUserFactory<T, I> userFactory) {
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
