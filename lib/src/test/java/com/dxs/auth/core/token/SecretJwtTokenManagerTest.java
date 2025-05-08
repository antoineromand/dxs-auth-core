package com.dxs.auth.core.token;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.dxs.auth.core.entity.RoleEnum;

public class SecretJwtTokenManagerTest {

    private SecretJwtTokenManager secretJwtTokenManager;
    private String secretKey;
    
    @BeforeEach
    public void setUp() {
        this.secretKey = "7PHnU2qhB8vph36+YErzG9MBZLZVW4A6NP7qH3sNdVI=";
        this.secretJwtTokenManager = new SecretJwtTokenManager(this.secretKey);
    }

    @Test
    void shouldGenerateToken() {
        UUID id = UUID.randomUUID();
        RoleEnum role = RoleEnum.REGULAR;
        long expiration = 900;

        String result = this.secretJwtTokenManager.generateToken(id, role, expiration);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertTrue(result.length() > 10);
    }

    @Test
    void shouldThrowErrorWhileGenerateToken() {
        UUID id = null;
        RoleEnum role = null;
        long expiration = 900;
        
        assertThrows(NullPointerException.class, () -> this.secretJwtTokenManager.generateToken(id, role, expiration));

    }

    @Test
    void shouldVerifyToken() {
        UUID id = UUID.randomUUID();
        RoleEnum role = RoleEnum.REGULAR;
        long expiration = 900;

        String result = this.secretJwtTokenManager.generateToken(id, role, expiration);

        assertTrue(this.secretJwtTokenManager.isTokenValid(result));
    }

    @Test
    void shouldThrowErrorWhileVerifyTokenValidity() {
        String emptyToken = "";
        String nullToken = null;
        String invalidToken = "invalidtoken.jwtfzfzef.fzefzefezfezf";

        assertThrows(IllegalArgumentException.class, () -> this.secretJwtTokenManager.isTokenValid(emptyToken));
        assertThrows(IllegalArgumentException.class, () -> this.secretJwtTokenManager.isTokenValid(nullToken));

        assertFalse(this.secretJwtTokenManager.isTokenValid(invalidToken));

    }
}
