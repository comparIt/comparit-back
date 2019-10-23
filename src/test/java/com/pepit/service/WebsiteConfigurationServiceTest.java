package com.pepit.service;

import com.pepit.exception.ReferentielRequestException;
import com.pepit.model.Model;
import com.pepit.model.WebsiteConfiguration;
import com.pepit.repository.WebsiteConfigurationRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
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

    private List<Model> modelList;
    private Model model;
    private Integer modelId;

    @Before
    public void initTests() {
        initDatas();
        initMocks();
    }

    private void initDatas() {
        modelId = 1;
        model = Model.builder()
                .id(modelId)
                .name("model_test")
                .technicalName("test")
                .isActivated(true)
                .modelProperties(new ArrayList<>())
                .createdAt(null)
                .updatedAt(null)
                .build();

        modelList = new ArrayList<>();
        modelList.add(model);

        websiteConfigurationId = 1;
        websiteConfiguration = WebsiteConfiguration.builder()
                .id(websiteConfigurationId)
                .color1("blue")
                .color2("white")
                .color3("red")
                .logo("/tmp/logo.svg")
                .featAnalytic(true)
                .modelList(modelList)
                .createdAt(null)
                .updatedAt(null)
                .build();
    }

    private void initMocks() {
        Mockito.when(websiteConfigurationRepository.findById(websiteConfigurationId)).thenReturn(Optional.ofNullable(websiteConfiguration));
        Mockito.when(websiteConfigurationRepository.save(websiteConfiguration)).thenReturn(websiteConfiguration);
    }

    @Test
    public void testSample() {
        assertTrue(true);
    }

    @Test
    public void findOneByIdOk() throws ReferentielRequestException {
        Assert.assertEquals(websiteConfiguration, websiteConfigurationService.findOneById(websiteConfigurationId));
    }

    @Test(expected = ReferentielRequestException.class)
    public void findOneByIdEmpty() throws ReferentielRequestException {
        Mockito.when(websiteConfigurationRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        websiteConfigurationService.findOneById(websiteConfigurationId);
    }

    @Test
    public void saveWebconfigurationOk() {
        Assert.assertEquals(websiteConfiguration, websiteConfigurationService.save(websiteConfiguration));
    }

    @Test
    public void saveWebconfigurationKO() {
        Mockito.when(websiteConfigurationRepository.save(Mockito.any())).thenThrow(new ReferentielRequestException());
        websiteConfigurationService.save(websiteConfiguration);
    }

}
