package com.library.service;

import com.library.configuration.PasswordEncoderConfig;
import com.library.model.User;
import com.library.repository.UserRepository;
import com.library.repository.user.UserTestUtility;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoderConfig passwordEncoderConfig;
    @Mock PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    public void shouldSaveUserWithEncryptedPassword() {
        User user = UserTestUtility.getTestUser();
        String rawPassword = user.getPassword();
        String encodedPassword = "encodedPassword";

        when(passwordEncoderConfig.passwordEncoder()).thenReturn(passwordEncoder);
        when(passwordEncoder.encode(rawPassword)).thenReturn(encodedPassword);

        userService.signUser(user);

        verify(userRepository, times(1)).save(user);
        verify(passwordEncoder, times(1)).encode(rawPassword);
        assertEquals(encodedPassword, user.getPassword(), "Password should be encrypted");
    }
}
