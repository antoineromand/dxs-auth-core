package com.dxs.auth.core.entity;

import com.dxs.auth.core.input.RegisterInput;

public interface IUserFactory <T extends AbstractUser, I extends RegisterInput> {
    T createUser(I customInput, String encryptPassword);
}
