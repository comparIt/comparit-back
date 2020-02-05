package com.pepit.controllers;

import com.pepit.constants.Routes;
import com.pepit.dto.UserDto;
import com.pepit.model.User;
import com.pepit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PutMapping("/saveSupplier")
    public ResponseEntity<UserDto> createSupplier(@RequestBody UserDto userDto) {
        return ResponseEntity.status(200).body(userService.createSupplier(userDto));
    }

    @PostMapping("/updateToSupplier")
    public ResponseEntity<User> UpdateToSupplier(@RequestParam Integer userId) {
        return ResponseEntity.status(200).body(userService.updateToSupplier(userId));
    }

    @GetMapping("/currentUser")
    public ResponseEntity<UserDto> getCurrentUserByToken() {
        return ResponseEntity.status(200).body(userService.getUserDtoByToken());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") Integer userId) {
        return ResponseEntity.status(200).body(userService.getUserById(userId));
    }
}
