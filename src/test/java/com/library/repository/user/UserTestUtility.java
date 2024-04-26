package com.library.repository.user;

import com.library.model.User;

public class UserTestUtility {

    public static User getTestUser() {
        return User.builder()
                .username("TestUser")
                .email("testuser@test.com")
                .password("Password123!")
                .build();
    }
}
