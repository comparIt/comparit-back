package com.pepit.service;

import com.pepit.CompareITBackApplicationTests;
import com.pepit.exception.ReferentielRequestException;
import com.pepit.model.Model;
import com.pepit.model.WebsiteConfiguration;
import com.pepit.repository.WebsiteConfigurationRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;

public class WebsiteConfigurationServiceTest extends CompareITBackApplicationTests {

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
                .activated(true)
                .modelProperties(new ArrayList<>())
                .createdAt(null)
                .updatedAt(null)
                .build();

        modelList = new ArrayList<>();
        modelList.add(model);

        websiteConfigurationId = 1;
        websiteConfiguration = WebsiteConfiguration.builder()
                .adminId(websiteConfigurationId)
                .colorPrimary("blue")
                .colorSecondary("white")
                .colorSecondary2("red")
                .logo("/tmp/logo.svg")
                .featAnalytic(true)
                .models(modelList)
                .createdAt(null)
                .updatedAt(null)
                .build();
    }

    private void initMocks() {
        Mockito.when(websiteConfigurationRepository.findById(websiteConfigurationId)).thenReturn(Optional.ofNullable(websiteConfiguration));
        Mockito.when(websiteConfigurationRepository.save(websiteConfiguration)).thenReturn(websiteConfiguration);
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


    @Test(expected = Exception.class)
    public void saveWebconfigurationKO() {
        Mockito.when(websiteConfigurationRepository.save(websiteConfiguration)).thenThrow(new Exception());
        websiteConfigurationService.save(websiteConfiguration);
    }

}
