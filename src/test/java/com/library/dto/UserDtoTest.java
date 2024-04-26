package com.library.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDtoTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void testValidUserDto() {
        UserDto userDto = UserDtoUtility.createUserDto();
        Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
        assertThat(violations.size()).isEqualTo(0);
    }

    @Test
    public void testWeakPasswordInvalidEmail() {
        UserDto userDto = UserDtoUtility.invalidUserDto();
        Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
        assertThat(violations.size()).isEqualTo(3);
    }

    @Test
    public void testBlankUserDto() {
        UserDto userDto = UserDtoUtility.blankUserDto();
        Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
        assertThat(violations.size()).isEqualTo(6);
    }
}
