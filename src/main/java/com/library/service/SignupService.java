package com.library.service;

import com.library.model.User;
import com.library.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class SignupService {

    private final UserRepository userRepository;

    public SignupService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean registerUser(User user) {
        if (!isUserValid(user)) {
            return false;
        }
        // Perform registration logic
        userRepository.save(user);
        return true;
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
