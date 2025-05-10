package com.dxs.auth.core.usecase.login;

import com.dxs.auth.core.entity.AbstractUser;
import com.dxs.auth.core.output.Response;
import com.dxs.auth.core.output.TwoFactorLoginResponse;
import com.dxs.auth.core.service.AuthenticatorService;

public class LoginUseCaseFactory<T extends AbstractUser> {
    private final ILoginUseCase<T,String> basicLoginUseCase;
    private final ILoginUseCase<T,TwoFactorLoginResponse> twoFactorLoginUseCase;
    private final AuthenticatorService<T> authenticatorService;

    public LoginUseCaseFactory(
            ILoginUseCase<T, String> basicLoginUseCase,
            ILoginUseCase<T, TwoFactorLoginResponse> twoFactorLoginUseCase,
            AuthenticatorService<T> authenticatorService
    ) {
        this.basicLoginUseCase = basicLoginUseCase;
        this.twoFactorLoginUseCase = twoFactorLoginUseCase;
        this.authenticatorService = authenticatorService;
    }

    public Response<?> login(String email, String password) {
        T user = this.authenticatorService.authenticate(email, password);

        if (user.is2FAActive()) {
            return twoFactorLoginUseCase.login(user);
        } else {
            return basicLoginUseCase.login(user);
        }
    }
}
