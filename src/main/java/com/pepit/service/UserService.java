package com.pepit.service;

import com.pepit.dto.UserDto;
import com.pepit.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserDto create(UserDto userDto);

    User getUserByToken();

}
