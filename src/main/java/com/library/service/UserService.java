package com.library.service;

import com.library.configuration.PasswordEncoderConfig;
import com.library.model.User;
import com.library.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoderConfig passwordEncoderConfig;

    public UserService(UserRepository userRepository, PasswordEncoderConfig passwordEncoderConfig) {
        this.userRepository = userRepository;
        this.passwordEncoderConfig = passwordEncoderConfig;
    }

    public void signUser(User user) {
        // Perform registration logic
        user.setPassword(passwordEncoderConfig.passwordEncoder()
                .encode(user.getPassword()));
        userRepository.save(user);
    }

    private boolean isUserValid(User user) {
        return isUniqueEmail(user.getEmail()) && isUniqueUsername(user.getUsername());
    }

    private boolean isUniqueEmail(String email) {
        return userRepository.findByEmail(email).isEmpty();
    }

    private boolean isUniqueUsername(String username) {
        return userRepository.findByUsername(username).isEmpty();
    }
}
