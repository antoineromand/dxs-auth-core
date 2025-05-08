package com.dxs.auth.core.usecase;

import com.dxs.auth.core.token.ITokenManager;

public class VerifyTokenUseCase {
    private final ITokenManager tokenManager;

    public VerifyTokenUseCase(ITokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    public boolean execute(String jwt) {
        return this.tokenManager.isTokenValid(jwt);
    }
}
