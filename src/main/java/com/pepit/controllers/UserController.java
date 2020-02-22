package com.pepit.controllers;

import com.pepit.constants.Routes;
import com.pepit.dto.UserDto;
import com.pepit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Routes.USER)
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/saveUser")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return ResponseEntity.status(200).body(userService.create(userDto));
    }

    @GetMapping("/currentUser")
    public ResponseEntity<UserDto> getCurrentUserByToken(){
        return ResponseEntity.status(200).body(userService.getUserDtoByToken());
    }

}
