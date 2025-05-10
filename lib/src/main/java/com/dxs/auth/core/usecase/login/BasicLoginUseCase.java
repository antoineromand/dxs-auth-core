package com.dxs.auth.core.usecase.login;

import com.dxs.auth.core.entity.AbstractUser;
import com.dxs.auth.core.output.Response;
import com.dxs.auth.core.token.ITokenManager;

public class BasicLoginUseCase<T extends AbstractUser> implements ILoginUseCase<T, String> {
    private final ITokenManager tokenManager;
    
    public BasicLoginUseCase(ITokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    @Override
    public Response<String> login(T user) {
        String token = tokenManager.generateToken(user.getId(), user.getRole(), 900);
        return Response.success(token);
    }
}
