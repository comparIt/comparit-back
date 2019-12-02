package com.pepit.controllers;

import com.pepit.business.UserBusiness;
import com.pepit.constants.Routes;
import com.pepit.dto.UserDto;
import com.pepit.model.User;
import com.pepit.service.UserService;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Routes.USER)
public class UserController {

    private UserBusiness userBusiness;

    @Autowired
    private UserService userService;


    @CrossOrigin
    @PutMapping(value = "/saveUser",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.status(200).body(userBusiness.saveUser(user));
    }

}
