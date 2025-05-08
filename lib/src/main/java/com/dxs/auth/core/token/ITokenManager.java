package com.dxs.auth.core.token;

import java.util.UUID;

import com.dxs.auth.core.entity.RoleEnum;

public interface ITokenManager {
    public String generateToken(UUID id, RoleEnum role, long expiration);
    public boolean isTokenValid(String token);
}
