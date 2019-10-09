package com.pepit.controller;

import com.pepit.business.FilterBusiness;
import com.pepit.controllers.FilterController;
import com.pepit.repository.FilterRepository;
import com.pepit.service.FilterService;
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
@WebMvcTest(value = FilterController.class)
public class FilterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FilterBusiness filterBusiness;

    @MockBean
    private FilterService filterService;

    @MockBean
    private FilterRepository filterRepository;

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
