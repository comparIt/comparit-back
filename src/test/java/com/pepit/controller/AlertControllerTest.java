package com.pepit.controller;

import com.pepit.business.AlertBusiness;
import com.pepit.controllers.AlertController;
import com.pepit.repository.AlertRepository;
import com.pepit.service.AlertService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AlertController.class)
public class AlertControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlertBusiness alertBusiness;

    @MockBean
    private AlertService alertService;

    @MockBean
    private AlertRepository alertRepository;

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
