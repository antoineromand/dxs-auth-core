package com.dxs.auth.core.usecase;

import com.dxs.auth.core.external.TokenManager;

public class VerifyTokenUseCase {
    private TokenManager tokenManager;

    public VerifyTokenUseCase(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    public boolean execute(String jwt) {
        return this.tokenManager.isTokenValid(jwt);
    }
}
