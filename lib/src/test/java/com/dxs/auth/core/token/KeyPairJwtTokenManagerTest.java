package com.dxs.auth.core.token;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.dxs.auth.core.entity.RoleEnum;

class KeyPairJwtTokenManagerTest {

    private KeyPairJwtTokenManager keyPairJwtTokenManager;

    private PrivateKey privateKey;
    private PublicKey publicKey;

    @BeforeEach
    void setUp() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair pair = keyGen.generateKeyPair();

        privateKey = pair.getPrivate();
        publicKey = pair.getPublic();

        keyPairJwtTokenManager = new KeyPairJwtTokenManager(publicKey, privateKey);
    }

    @Test
    void shouldGenerateAndValidateValidToken() {
        UUID userId = UUID.randomUUID();
        String token = keyPairJwtTokenManager.generateToken(userId, RoleEnum.ADMIN, 3600);

        assertNotNull(token);
        assertTrue(token.length() > 10);
        assertFalse(token.isEmpty());

        boolean isValid = keyPairJwtTokenManager.isTokenValid(token);
        assertTrue(isValid, "Expected token to be valid");
    }

    @Test
    void shouldRejectInvalidToken() {
        String fakeToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." +
                "eyJzdWIiOiIxMjM0NTY3ODkwIiwicm9sZSI6IkFETUlOIiwiaWF0IjoxNTE2MjM5MDIyfQ." +
                "INVALIDSIGNATURE";

        boolean isValid = keyPairJwtTokenManager.isTokenValid(fakeToken);
        assertFalse(isValid, "Expected token to be invalid");

        assertFalse(this.keyPairJwtTokenManager.isTokenValid(null));
    }
}
