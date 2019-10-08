package com.pepit.controllers;

import com.pepit.business.UserBusiness;
import com.pepit.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private UserBusiness userBusiness;
    private UserService userService;

    public UserController(UserBusiness userBusiness, UserService userService) {
        this.userBusiness = userBusiness;
        this.userService = userService;
    }
}
