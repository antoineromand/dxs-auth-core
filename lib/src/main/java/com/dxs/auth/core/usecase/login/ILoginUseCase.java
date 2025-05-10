package com.dxs.auth.core.usecase.login;

import com.dxs.auth.core.entity.AbstractUser;
import com.dxs.auth.core.output.Response;

public interface ILoginUseCase<T extends AbstractUser, R> {
    Response<R> login(T user);
}
