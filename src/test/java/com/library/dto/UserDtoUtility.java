package com.library.dto;

public class UserDtoUtility {

    public static UserDto createUserDto() {
        UserDto userDto = new UserDto();
        userDto.setUsername("TestUser");
        userDto.setEmail("testuser@test.com");
        userDto.setPassword("Password123!");
        return userDto;
    }

    public static UserDto invalidUserDto() {
        UserDto userDto = new UserDto();
        userDto.setUsername("TestUserWithMoreThen10Words");
        userDto.setEmail("InvalidEmail");
        userDto.setPassword("PasswordNoMarks");
        return userDto;
    }

    public static UserDto blankUserDto() {
        UserDto userDto = new UserDto();
        userDto.setUsername("");
        userDto.setEmail("");
        userDto.setPassword("");
        return  userDto;
    }
}
