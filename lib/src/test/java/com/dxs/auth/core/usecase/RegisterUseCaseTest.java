package com.dxs.auth.core.usecase;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dxs.auth.core.entity.AbstractUser;
import com.dxs.auth.core.entity.IUserFactory;
import com.dxs.auth.core.entity.RoleEnum;
import com.dxs.auth.core.exceptions.EmailAlreadyExistsException;
import com.dxs.auth.core.external.IPasswordEncrypt;
import com.dxs.auth.core.input.RegisterInput;
import com.dxs.auth.core.output.Response;
import com.dxs.auth.core.repository.AbstractUserRepository;

@ExtendWith(MockitoExtension.class)
class RegisterUseCaseTest {

    private AbstractUserRepository<MockUser> userRepository;
    private IPasswordEncrypt passwordEncrypt;
    private IUserFactory<MockUser, MockRegisterInput> userFactory;

    private RegisterUseCase<MockUser, MockRegisterInput> useCase;

    @BeforeEach
    void setUp() {
        userRepository = mock(AbstractUserRepository.class);
        passwordEncrypt = mock(IPasswordEncrypt.class);
        userFactory = mock(IUserFactory.class);
        useCase = new RegisterUseCase<>(userRepository, passwordEncrypt, userFactory);
    }

    @Test
    void should_register_user_successfully() {
        MockRegisterInput input = new MockRegisterInput("test@example.com", "plainPassword");
        String encryptedPassword = "encryptedPassword";
        MockUser newUser = new MockUser(UUID.randomUUID(), input.getEmail(), encryptedPassword);

        when(userRepository.findByEmail(input.getEmail())).thenReturn(Optional.empty());
        when(passwordEncrypt.encrypt(input.getPassword())).thenReturn(encryptedPassword);
        when(userFactory.createUser(input, encryptedPassword)).thenReturn(newUser);
        when(userRepository.save(newUser)).thenReturn(newUser);

        Response<MockUser> response = useCase.register(input);

        assertTrue(response.isSuccess());
        assertEquals(input.getEmail(), response.getData().getEmail());
        verify(userRepository).save(newUser);
    }

    @Test
    void should_throw_exception_when_email_already_exists() {
        MockRegisterInput input = new MockRegisterInput("test@example.com", "plainPassword");
        MockUser existingUser = new MockUser(UUID.randomUUID(), input.getEmail(), "pass");

        when(userRepository.findByEmail(input.getEmail())).thenReturn(Optional.of(existingUser));

        assertThrows(EmailAlreadyExistsException.class, () -> useCase.register(input));
        verify(userRepository, never()).save(any());
    }

    static class MockUser extends AbstractUser {
        private final UUID id;
        private final String email;
        private final String password;

        public MockUser(UUID id, String email, String password) {
            this.id = id;
            this.email = email;
            this.password = password;
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
            return RoleEnum.REGULAR;
        }
    }

    static class MockRegisterInput implements RegisterInput {
        private final String email;
        private final String password;

        public MockRegisterInput(String email, String password) {
            this.email = email;
            this.password = password;
        }

        @Override
        public String getEmail() {
            return email;
        }

        @Override
        public String getPassword() {
            return password;
        }
    }
}
