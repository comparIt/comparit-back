package com.pepit.converters;

import com.pepit.dto.UserDto;
import com.pepit.model.User;
import com.pepit.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class UserConverter extends GenericsConverter<User, UserDto> {
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public UserDto entityToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRoles().stream().sorted(Comparator.reverseOrder()).findFirst().get())
                .companyId(Long.valueOf(user.getCompany().getId()))
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
                .company(companyRepository.findById(userDto.getCompanyId().intValue()).get())
                .createdAt(userDto.getCreatedAt())
                .updatedAt(userDto.getUpdatedAt()).build();
    }
}
