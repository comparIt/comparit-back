package com.pepit.service;

import com.pepit.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserDto create(UserDto userDto);
}