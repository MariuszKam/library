package com.library.mapper;

import com.library.dto.UserDto;
import com.library.model.User;

public class UserMapper {

    public static User mapToUser(UserDto userDto) {
        return User.builder()
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
    }
}
