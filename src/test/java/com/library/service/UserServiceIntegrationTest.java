package com.library.service;

import com.library.configuration.PasswordEncoderConfig;
import com.library.model.User;
import com.library.repository.UserRepository;
import com.library.repository.user.UserTestUtility;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class UserServiceIntegrationTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoderConfig passwordEncoderConfig;

    @Test
    public void signUser_shouldSaveUserWithEncodedPassword() {
        User user = UserTestUtility.getTestUser();
        System.out.println(user.getPassword());

//        userService.signUser(user);

        User savedUser = userRepository.findByUsername(user.getUsername()).orElse(null);
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getEmail()).isEqualTo(user.getEmail());
        assertThat(passwordEncoderConfig.passwordEncoder()
                .matches(savedUser.getPassword(), user.getPassword())).isTrue();
    }
}
