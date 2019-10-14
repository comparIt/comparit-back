package com.pepit.controller;

import com.pepit.business.WebsiteConfigurationBusiness;
import com.pepit.controllers.WebsiteConfigurationController;
import com.pepit.exception.DataProvidedException;
import com.pepit.exception.ReferentielRequestException;
import com.pepit.model.WebsiteConfiguration;
import com.pepit.repository.WebsiteConfigurationRepository;
import com.pepit.service.WebsiteConfigurationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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
    private Integer websiteConfigurationId = 1;

    @Before
    public void initTests() throws ReferentielRequestException, DataProvidedException {
        initDatas();
        initMocks();
    }

    private void initDatas() {
        websiteConfiguration = WebsiteConfiguration.builder()
                .id(websiteConfigurationId)
                .color1("blue")
                .color2("white")
                .color3("red")
                .logo("/tmp/logo.svg")
                .featAnalytic(true)
                .createdAt(null)
                .updatedAt(null)
                .build();
    }

    private void initMocks() throws ReferentielRequestException, DataProvidedException {
        Mockito.when(websiteConfigurationService.findOneById(websiteConfigurationId)).thenReturn(websiteConfiguration);
    }

    @Test
    public void testSample() {
        assertTrue(true);
    }

    @Test
    public void getWebsiteConfigByIdOk() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/websiteconfig/get?websiteConfigurationId=" + websiteConfigurationId)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        JSONAssert.assertEquals(websiteConfiguration.toString(), result.getResponse().getContentAsString(), true);
    }

    @Test
    public void getWebsiteConfigByIdNoIdGiven() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/websiteconfig/get?websiteConfigurationId=")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expected = "code erreur";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
    }

    @Test
    public void getWebsiteConfigByIdButNoConfigExist() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/websiteconfig/get?websiteConfigurationId=" + websiteConfigurationId)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expected = "code erreur 2";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
    }
}
