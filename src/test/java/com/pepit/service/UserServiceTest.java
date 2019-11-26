package com.pepit.service;

import com.pepit.CompareITBackApplicationTests;
import com.pepit.converters.UserConverter;
import com.pepit.repository.AlertRepository;
import com.pepit.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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


    @Before
    public void initTests() {
        initDatas();
        initMocks();
    }

    private void initDatas() {

    }

    private void initMocks() {

    }

    @Test
    public void testSample() {
        assertTrue(true);
    }
}
