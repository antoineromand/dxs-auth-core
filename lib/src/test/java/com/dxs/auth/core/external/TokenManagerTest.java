package com.dxs.auth.core.external;

import com.dxs.auth.core.entity.RoleEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.*;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TokenManagerTest {

    private TokenManager tokenManager;

    private PrivateKey privateKey;
    private PublicKey publicKey;

    @BeforeEach
    void setUp() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair pair = keyGen.generateKeyPair();

        privateKey = pair.getPrivate();
        publicKey = pair.getPublic();

        tokenManager = new TokenManager(publicKey, privateKey);
    }

    @Test
    void shouldValidateValidToken() {
        UUID userId = UUID.randomUUID();
        String token = tokenManager.generateToken(userId, RoleEnum.ADMIN, 3600); // 1h

        boolean isValid = tokenManager.isTokenValid(token);
        assertTrue(isValid, "Expected token to be valid");
    }

    @Test
    void shouldRejectInvalidToken() {
        String fakeToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." +
                "eyJzdWIiOiIxMjM0NTY3ODkwIiwicm9sZSI6IkFETUlOIiwiaWF0IjoxNTE2MjM5MDIyfQ." +
                "INVALIDSIGNATURE";

        boolean isValid = tokenManager.isTokenValid(fakeToken);
        assertFalse(isValid, "Expected token to be invalid");
    }
}
