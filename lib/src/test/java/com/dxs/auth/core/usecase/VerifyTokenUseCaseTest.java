package com.dxs.auth.core.usecase;

import com.dxs.auth.core.external.TokenManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VerifyTokenUseCaseTest {

    private TokenManager tokenManager;
    private VerifyTokenUseCase verifyTokenUseCase;

    @BeforeEach
    void setUp() {
        tokenManager = mock(TokenManager.class);
        verifyTokenUseCase = new VerifyTokenUseCase(tokenManager);
    }

    @Test
    void shouldReturnTrue_whenTokenIsValid() {
        String jwt = "valid.jwt.token";
        when(tokenManager.isTokenValid(jwt)).thenReturn(true);

        boolean result = verifyTokenUseCase.execute(jwt);

        assertTrue(result);
        verify(tokenManager).isTokenValid(jwt);
    }

    @Test
    void shouldReturnFalse_whenTokenIsInvalid() {
        String jwt = "invalid.jwt.token";
        when(tokenManager.isTokenValid(jwt)).thenReturn(false);

        boolean result = verifyTokenUseCase.execute(jwt);

        assertFalse(result);
        verify(tokenManager).isTokenValid(jwt);
    }
}
