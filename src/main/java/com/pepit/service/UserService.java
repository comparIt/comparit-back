package com.pepit.service;

import com.pepit.dto.UserDto;
import com.pepit.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserDto create(UserDto userDto);

    User getUserByToken();

    UserDto getUserDtoByToken();

    User updateToSupplier(Integer userId);

    UserDto createSupplier(UserDto userDto);

    User getUserById(Integer userId);
}
