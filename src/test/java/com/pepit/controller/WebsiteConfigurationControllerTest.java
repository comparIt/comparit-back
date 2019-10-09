package com.pepit.controller;

import com.pepit.business.WebsiteConfigurationBusiness;
import com.pepit.controllers.WebsiteConfigurationController;
import com.pepit.model.WebsiteConfiguration;
import com.pepit.repository.WebsiteConfigurationRepository;
import com.pepit.service.WebsiteConfigurationService;
import lombok.Lombok;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@WebMvcTest(value = WebsiteConfigurationController.class)
public class WebsiteConfigurationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WebsiteConfigurationBusiness websiteConfigurationBusiness;

    @MockBean
    private WebsiteConfigurationService websiteConfigurationService;

    @MockBean
    private WebsiteConfigurationRepository websiteConfigurationRepository;

    private WebsiteConfiguration websiteConfiguration;


    @Before
    public void initTests() {
        initDatas();
        initMocks();
    }

    private void initDatas() {
        websiteConfiguration = websiteConfiguration.builder()
                .color1("blue")
                .color2("white")
                .color3("red")
                .logo("/tmp/logo.svg")
                .featAnalytic(true).build();

    }

    private void initMocks() {
    }

    @Test
    public void testSample() {
        assertTrue(true);
    }
}
