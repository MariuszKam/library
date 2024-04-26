package com.library.service;

import com.library.configuration.PasswordEncoderConfig;
import com.library.dto.UserDto;
import com.library.model.User;
import com.library.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoderConfig passwordEncoderConfig;

    public UserService(UserRepository userRepository, PasswordEncoderConfig passwordEncoderConfig) {
        this.userRepository = userRepository;
        this.passwordEncoderConfig = passwordEncoderConfig;
    }

    public void signUser(UserDto userDto) {


        // Perform registration logic
//        user.setPassword(passwordEncoderConfig.passwordEncoder()
//                .encode(user.getPassword()));
//        userRepository.save(user);
    }
}
