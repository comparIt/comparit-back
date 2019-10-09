package com.pepit.controller;

import com.pepit.business.ModelPropertyBusiness;
import com.pepit.controllers.ModelPropertyController;
import com.pepit.repository.ModelPropertyRepository;
import com.pepit.service.ModelPropertyService;
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
@WebMvcTest(value = ModelPropertyController.class)
public class ModelPropertyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ModelPropertyBusiness modelPropertyBusiness;

    @MockBean
    private ModelPropertyService modelService;

    @MockBean
    private ModelPropertyRepository modelRepository;

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
