package com.pepit.controllers;

import com.pepit.constants.Routes;
import com.pepit.dto.UserDto;
import com.pepit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Routes.USER)
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(consumes={"application/json"})
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return ResponseEntity.status(201).body(this.userService.create(userDto));
    }
}
