package com.dxs.auth.core.usecase;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.dxs.auth.core.token.ITokenManager;

class VerifyTokenUseCaseTest {

    private ITokenManager tokenManager;
    private VerifyTokenUseCase verifyTokenUseCase;

    @BeforeEach
    void setUp() {
        tokenManager = mock(ITokenManager.class);
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
