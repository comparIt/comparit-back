package com.pepit.service;

import com.pepit.CompareITBackApplicationTests;
import com.pepit.constants.Roles;
import com.pepit.converters.UserConverter;
import com.pepit.dto.UserDto;
import com.pepit.model.Company;
import com.pepit.model.User;
import com.pepit.repository.AlertRepository;
import com.pepit.repository.UserRepository;
import com.pepit.security.Hashing;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.assertTrue;

public class UserServiceTest extends CompareITBackApplicationTests {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserConverter userConverter;

    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private User user;
    private String passwordNotEncrypted;
    private Collection roles;
    private UserDto userDto;

    @Before
    public void initTests() {
        initDatas();
        initMocks();
    }

    private void initDatas() {
        passwordNotEncrypted = "test";
        roles = Collections.singletonList(Roles.ROLE_CUSTOMER);

        user = User.builder()
                .id(1L)
                .email("test@test.fr")
                .password(passwordNotEncrypted)
                .lastName("lastName")
                .firstName("test")
                .filters(new ArrayList<>())
                .company(new Company())
                .createdAt(null)
                .updatedAt(null)
                .roles(null)
                .build();

        userDto = UserDto.builder()
                .id(1L)
                .email("test@test.fr")
                .password(passwordNotEncrypted)
                .firstName("test")
                .lastName("lastName")
                .filters(new ArrayList<>())
                .companyId(null)
                .role(null)
                .createdAt(null)
                .updatedAt(null)
                .build();
    }

    private void initMocks() {
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        Mockito.when(userConverter.dtoToEntity(userDto)).thenReturn(user);
    }


    @Test
    public void createCustomerOK(){
        userService.create(userDto);
        Mockito.verify(userConverter, Mockito.times(1)).dtoToEntity(userDto);
        Mockito.verify(bCryptPasswordEncoder, Mockito.times(1)).encode(Hashing.sha256(userDto.getPassword()));
        Mockito.verify(userRepository, Mockito.times(1)).save(user);
        Mockito.verify(userConverter, Mockito.times(1)).entityToDto(user);
    }

    @Test(expected = Exception.class)
    public void createUserKORepo(){
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenThrow(new Exception());
        userService.create(userDto);
    }




}
