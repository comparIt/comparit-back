package com.pepit.service.impl;

import com.pepit.constants.Errors;
import com.pepit.converters.UserConverter;
import com.pepit.dto.UserDto;
import com.pepit.exception.BadCredentialException;
import com.pepit.model.User;
import com.pepit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    @Lazy
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserConverter userConverter;

    public UserDto login(UserDto userDto) {
        Optional<User> dbUser = userRepository.findByEmail(userDto.getEmail());
        if (!dbUser.isPresent() || userDto.getPassword() == null || !bCryptPasswordEncoder.matches(userDto.getPassword(), dbUser.get().getPassword())) {
            throw new BadCredentialException(Errors.AUTH_BAD_USERNAME_PASSWORD);
        }
        return userConverter.entityToDto(dbUser.get());
    }
}
