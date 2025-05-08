package com.dxs.auth.core.token;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.dxs.auth.core.entity.RoleEnum;

public class SecretJwtTokenTest {

    private SecretJwtToken secretJwtToken;
    private String secretKey;
    
    @BeforeEach
    public void setUp() {
        this.secretKey = "7PHnU2qhB8vph36+YErzG9MBZLZVW4A6NP7qH3sNdVI=";
        this.secretJwtToken = new SecretJwtToken(this.secretKey);
    }

    @Test
    void shouldGenerateToken() {
        UUID id = UUID.randomUUID();
        RoleEnum role = RoleEnum.REGULAR;
        long expiration = 900;

        String result = this.secretJwtToken.generateToken(id, role, expiration);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertTrue(result.length() > 10);
    }

    @Test
    void shouldThrowErrorWhileGenerateToken() {
        UUID id = null;
        RoleEnum role = null;
        long expiration = 900;
        
        assertThrows(NullPointerException.class, () -> this.secretJwtToken.generateToken(id, role, expiration));

    }

    @Test
    void shouldVerifyToken() {
        UUID id = UUID.randomUUID();
        RoleEnum role = RoleEnum.REGULAR;
        long expiration = 900;

        String result = this.secretJwtToken.generateToken(id, role, expiration);

        assertTrue(this.secretJwtToken.isTokenValid(result));
    }

    @Test
    void shouldThrowErrorWhileVerifyTokenValidity() {
        String emptyToken = "";
        String nullToken = null;
        String invalidToken = "invalidtoken.jwtfzfzef.fzefzefezfezf";

        assertThrows(IllegalArgumentException.class, () -> this.secretJwtToken.isTokenValid(emptyToken));
        assertThrows(IllegalArgumentException.class, () -> this.secretJwtToken.isTokenValid(nullToken));

        assertFalse(this.secretJwtToken.isTokenValid(invalidToken));

    }
}
