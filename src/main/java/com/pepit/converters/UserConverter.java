package com.pepit.converters;

import com.pepit.dto.UserDto;
import com.pepit.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter extends GenericsConverter<User, UserDto> {

    @Override
    public UserDto entityToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    @Override
    public User dtoToEntity(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .createdAt(userDto.getCreatedAt())
                .updatedAt(userDto.getUpdatedAt()).build();
    }
}
