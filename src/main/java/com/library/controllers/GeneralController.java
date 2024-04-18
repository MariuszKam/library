package com.library.controllers;

import com.library.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeneralController {

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {

    }

}
