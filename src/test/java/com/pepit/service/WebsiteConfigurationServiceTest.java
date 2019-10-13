package com.pepit.service;

import com.pepit.model.WebsiteConfiguration;
import com.pepit.repository.WebsiteConfigurationRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.text.html.Option;

import java.util.Optional;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@WebMvcTest(value = WebsiteConfigurationService.class)
public class WebsiteConfigurationServiceTest {

    @Autowired
    private WebsiteConfigurationService websiteConfigurationService;

    @MockBean
    private WebsiteConfigurationRepository websiteConfigurationRepository;

    private WebsiteConfiguration websiteConfiguration;
    private Integer websiteConfigurationId;

    @Before
    public void initTests() {
        initDatas();
        initMocks();
    }

    private void initDatas() {
        websiteConfigurationId = 1;
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

    private void initMocks() {
        Mockito.when(websiteConfigurationRepository.findOneById(websiteConfigurationId)).thenReturn(websiteConfiguration);
        Mockito.when(websiteConfigurationRepository.save(websiteConfiguration)).thenReturn(websiteConfiguration);
    }

    @Test
    public void testSample() {
        assertTrue(true);
    }

    @Test
    public void findOneByIdOk(){

    }

    @Test
    public void findOneByIdNotFound(){

    }

    @Test
    public void findOneByIdDbErr(){

    }

    @Test
    public void saveOk(){

    }

    @Test
    public void saveDbErr(){

    }


}
