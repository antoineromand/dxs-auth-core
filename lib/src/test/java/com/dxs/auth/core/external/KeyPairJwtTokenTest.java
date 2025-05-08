package com.dxs.auth.core.external;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.dxs.auth.core.entity.RoleEnum;
import com.dxs.auth.core.token.KeyPairJwtToken;

class KeyPairJwtTokenTest {

    private KeyPairJwtToken keyPairJwtToken;

    private PrivateKey privateKey;
    private PublicKey publicKey;

    @BeforeEach
    void setUp() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair pair = keyGen.generateKeyPair();

        privateKey = pair.getPrivate();
        publicKey = pair.getPublic();

        keyPairJwtToken = new KeyPairJwtToken(publicKey, privateKey);
    }

    @Test
    void shouldValidateValidToken() {
        UUID userId = UUID.randomUUID();
        String token = keyPairJwtToken.generateToken(userId, RoleEnum.ADMIN, 3600); // 1h

        boolean isValid = keyPairJwtToken.isTokenValid(token);
        assertTrue(isValid, "Expected token to be valid");
    }

    @Test
    void shouldRejectInvalidToken() {
        String fakeToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." +
                "eyJzdWIiOiIxMjM0NTY3ODkwIiwicm9sZSI6IkFETUlOIiwiaWF0IjoxNTE2MjM5MDIyfQ." +
                "INVALIDSIGNATURE";

        boolean isValid = keyPairJwtToken.isTokenValid(fakeToken);
        assertFalse(isValid, "Expected token to be invalid");
    }
}
