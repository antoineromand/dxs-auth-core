package com.dxs.auth.core.usecase;

import com.dxs.auth.core.entity.AbstractUser;
import com.dxs.auth.core.entity.RoleEnum;
import com.dxs.auth.core.exceptions.AuthenticationFailedException;
import com.dxs.auth.core.external.IPasswordEncrypt;
import com.dxs.auth.core.external.TokenManager;
import com.dxs.auth.core.external.repository.AbstractUserRepository;
import com.dxs.auth.core.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoginUseCaseTest {

    private AbstractUserRepository<MockUser> userRepository;
    private IPasswordEncrypt passwordEncrypt;
    private TokenManager tokenManager;

    private LoginUseCase<MockUser> loginUseCase;

    @BeforeEach
    void setUp() {
        userRepository = mock(AbstractUserRepository.class);
        passwordEncrypt = mock(IPasswordEncrypt.class);
        tokenManager = mock(TokenManager.class);
        loginUseCase = new LoginUseCase<>(userRepository, passwordEncrypt, tokenManager);
    }

    @Test
    void shouldLoginSuccessfully_whenCredentialsAreCorrect() {
        MockUser user = new MockUser(UUID.randomUUID(), "user@example.com", "hashed-password", RoleEnum.REGULAR);
        when(userRepository.findByEmail("user@example.com")).thenReturn(Optional.of(user));
        when(passwordEncrypt.match("hashed-password", "plain-password")).thenReturn(true);
        when(tokenManager.generateToken(user.getId(), user.getRole(), 900)).thenReturn("mocked-jwt");

        Response<String> response = loginUseCase.login("user@example.com", "plain-password");

        assertTrue(response.isSuccess());
        assertEquals("mocked-jwt", response.getData());
    }

    @Test
    void shouldThrow_whenEmailNotFound() {
        when(userRepository.findByEmail("notfound@example.com")).thenReturn(Optional.empty());

        assertThrows(AuthenticationFailedException.class,
                () -> loginUseCase.login("notfound@example.com", "pass"));
    }

    @Test
    void shouldThrow_whenPasswordDoesNotMatch() {
        MockUser user = new MockUser(UUID.randomUUID(), "user@example.com", "hashed-password", RoleEnum.REGULAR);
        when(userRepository.findByEmail("user@example.com")).thenReturn(Optional.of(user));
        when(passwordEncrypt.match("hashed-password", "wrong-password")).thenReturn(false);

        assertThrows(AuthenticationFailedException.class,
                () -> loginUseCase.login("user@example.com", "wrong-password"));
    }

    static class MockUser extends AbstractUser {
        private final UUID id;
        private final String email;
        private final String password;
        private final RoleEnum role;

        public MockUser(UUID id, String email, String password, RoleEnum role) {
            this.id = id;
            this.email = email;
            this.password = password;
            this.role = role;
        }

        @Override
        public UUID getId() {
            return id;
        }

        @Override
        public String getEmail() {
            return email;
        }

        @Override
        public String getPassword() {
            return password;
        }

        @Override
        public RoleEnum getRole() {
            return role;
        }
    }
}
