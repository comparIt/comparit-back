package com.pepit.service;

import com.pepit.dto.SupplierDto;
import com.pepit.dto.UserDto;
import com.pepit.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    UserDto create(UserDto userDto);

    User getUserByToken();

    UserDto getUserDtoByToken();

    User updateToSupplier(Integer userId);

    UserDto createSupplier(SupplierDto supplierDto);

    User getUserById(Integer userId);

    List<UserDto> getAllUsers();

    Integer deleteUser(Integer userId);

    UserDto update(UserDto userDto);
}
