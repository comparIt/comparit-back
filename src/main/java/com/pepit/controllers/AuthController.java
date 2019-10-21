package com.pepit.controllers;

import com.pepit.constants.Routes;
import com.pepit.dto.UserDto;
import com.pepit.exceptionHandler.ExceptionCatcher;
import com.pepit.service.impl.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Routes.AUTH)
public class AuthController extends ExceptionCatcher {

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<UserDto> auth(@RequestBody UserDto userDto) {
        UserDto user = authService.login(userDto);
        return ResponseEntity.status(200).body(user);
    }
}
