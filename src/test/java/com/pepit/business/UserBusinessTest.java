package com.pepit.business;

import com.pepit.CompareITBackApplicationTests;
import com.pepit.repository.AlertRepository;
import com.pepit.service.AlertService;
import com.pepit.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.Assert.assertTrue;

public class UserBusinessTest extends CompareITBackApplicationTests {

    @Autowired
    private UserBusiness userBusiness;

    @MockBean
    private UserService userService;

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
