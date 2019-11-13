package com.pepit.business;

import com.pepit.CompareITBackApplicationTests;
import com.pepit.exception.ReferentielRequestException;
import com.pepit.model.Model;
import com.pepit.model.WebsiteConfiguration;
import com.pepit.repository.WebsiteConfigurationRepository;
import com.pepit.service.ModelService;
import com.pepit.service.WebsiteConfigurationService;
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

import static org.junit.Assert.assertTrue;

public class WebsiteConfigurationBusinessTest extends CompareITBackApplicationTests {

    @Autowired
    private WebsiteConfigurationBusiness websiteConfigurationBusiness;

    @MockBean
    private WebsiteConfigurationService websiteConfigurationService;

    @MockBean
    private WebsiteConfigurationRepository websiteConfigurationRepository;

    @MockBean
    private ModelService modelService;

    private WebsiteConfiguration websiteConfiguration;
    private Integer websiteConfigurationId;

    private Model model;
    private Integer modelId;

    private List<Model> modelList = new ArrayList<>();

    @Before
    public void initTests() {
        initDatas();
        initMocks();
    }

    private void initDatas() {
        modelId = 1;
        websiteConfigurationId = 1;

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

        websiteConfiguration = WebsiteConfiguration.builder()
                .id(websiteConfigurationId)
                .color1("blue")
                .color2("white")
                .color3("red")
                .logo("/tmp/logo.svg")
                .featAnalytic(true)
                .createdAt(null)
                .updatedAt(null)
                .modelList(modelList)
                .build();
    }

    private void initMocks() {
        Mockito.when(websiteConfigurationService.save(websiteConfiguration)).thenReturn(websiteConfiguration);
        Mockito.when(modelService.save(model)).thenReturn(model);
    }

    @Test
    public void testSample() {
        assertTrue(true);
    }

    @Test
    public void saveWebsiteConfigOK() throws ReferentielRequestException {
        Assert.assertEquals(websiteConfiguration, websiteConfigurationBusiness.saveWebsiteConfiguration(websiteConfiguration));
        Mockito.verify(modelService, Mockito.times(1)).save(model);
        Mockito.verify(websiteConfigurationService, Mockito.times(1)).save(websiteConfiguration);
    }

//    @Test(expected = ReferentielRequestException.class)
//    public void saveWebsiteConfigKOSaveWebsite() throws ReferentielRequestException {
//        Mockito.when(websiteConfigurationService.save(websiteConfiguration)).thenThrow(new ReferentielRequestException());
//        websiteConfigurationBusiness.saveWebsiteConfiguration(websiteConfiguration);
//    }

//    @Test(expected = ReferentielRequestException.class)
//    public void saveWebsiteConfigKOSaveModel() throws ReferentielRequestException {
//        Mockito.when(modelService.save(model)).thenThrow(new ReferentielRequestException());
//        websiteConfigurationBusiness.saveWebsiteConfiguration(websiteConfiguration);
//
//    }
}
