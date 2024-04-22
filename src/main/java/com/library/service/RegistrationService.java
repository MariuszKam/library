package com.library.service;

import com.library.model.User;
import com.library.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private UserRepository userRepository;

    public RegistrationService(UserRepository userRepository) {
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
        //TODO: NEED TO MAKE CHANGES ABOUT ORDER!!
        return isEmailValid(user.getEmail()) &&
                isUsernameValid(user.getUsername()) &&
                isPasswordValid(user.getPassword()) &&
                isUniqueEmail(user.getEmail()) &&
                isUniqueUsername(user.getUsername());
    }

    private boolean isEmailValid(String email) {
        return true;
    }

    private boolean isUsernameValid(String username) {
        return true;
    }

    private boolean isPasswordValid(String password) {
        return true;
    }

    private boolean isUniqueEmail(String email) {
        return userRepository.findByEmail(email).isEmpty();
    }

    private boolean isUniqueUsername(String username) {
        return userRepository.findByUsername(username).isEmpty();
    }
}
