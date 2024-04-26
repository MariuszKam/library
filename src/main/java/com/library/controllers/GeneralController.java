package com.library.controllers;

import com.library.dto.UserDto;
import com.library.model.User;
import com.library.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeneralController {

    private UserService userService;

    public GeneralController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signupUser(@RequestBody UserDto user) {
        return ResponseEntity.ok(user);
    }

}
