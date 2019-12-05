package com.pepit.business;

import com.pepit.CompareITBackApplicationTests;
import com.pepit.constants.TypeModelPropertyEnum;
import com.pepit.exception.ReferentielRequestException;
import com.pepit.model.Model;
import com.pepit.model.ModelProperty;
import com.pepit.model.WebsiteConfiguration;
import com.pepit.service.ModelPropertyService;
import com.pepit.service.ModelService;
import com.pepit.service.WebsiteConfigurationService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class WebsiteConfigurationBusinessTest extends CompareITBackApplicationTests {

    @Autowired
    private WebsiteConfigurationBusiness websiteConfigurationBusiness;

    @MockBean
    private WebsiteConfigurationService websiteConfigurationService;

    @MockBean
    private ModelService modelService;

    @MockBean
    private ModelPropertyService modelPropertyService;


    private Model model;
    private ModelProperty modelProperty;
    private WebsiteConfiguration websiteConfiguration;

    private Integer modelId;
    private Integer websiteConfigurationId;

    private List<Model> modelList = new ArrayList<>();
    private List<ModelProperty> modelProperties = new ArrayList<>();

    @Before
    public void initTests() {
        initDatas();
        initMocks();
    }

    private void initDatas() {
        modelId = 1;
        websiteConfigurationId = 1;

        modelProperty = ModelProperty.builder()
                .id(modelId)
                .name("property_test")
                .technicalName("test")
                .type(TypeModelPropertyEnum.ENUMERATIVE)
                .filtrable(true)
                .filtrableAdvanced(false)
                .mandatory(false)
                .activated(true)
                .createdAt(null)
                .updatedAt(null)
                .build();
        modelProperties.add(modelProperty);

        model = Model.builder()
                .id(modelId)
                .name("model_test")
                .technicalName("test")
                .activated(true)
                .modelProperties(modelProperties)
                .createdAt(null)
                .updatedAt(null)
                .build();

        modelList = new ArrayList<>();

        modelList.add(model);

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
        Mockito.when(websiteConfigurationService.save(Mockito.any(WebsiteConfiguration.class))).thenReturn(websiteConfiguration);
        Mockito.when(modelService.save(Mockito.any(Model.class))).thenReturn(model);
        Mockito.when(modelPropertyService.save(Mockito.any(ModelProperty.class))).thenReturn(modelProperty);
        Mockito.when(modelPropertyService.saveAll(Mockito.anyList())).thenReturn(modelProperties);
        Mockito.when(modelService.saveAll(Mockito.anyList())).thenReturn(modelList);
    }

    @Test
    public void testSample() {
        assertTrue(true);
    }

    @Test
    public void saveWebsiteConfigOK() throws ReferentielRequestException {
        Assert.assertEquals(websiteConfiguration, websiteConfigurationBusiness.saveWebsiteConfiguration(websiteConfiguration));
        Mockito.verify(modelService, Mockito.times(1)).saveAll(modelList);
        Mockito.verify(modelPropertyService, Mockito.times(1)).saveAll(modelProperties);
        Mockito.verify(websiteConfigurationService, Mockito.times(1)).save(websiteConfiguration);
    }

    @Test(expected = ReferentielRequestException.class)
    public void saveWebsiteConfigKOSaveWebsite() throws ReferentielRequestException {
        Mockito.when(websiteConfigurationService.save(Mockito.any(WebsiteConfiguration.class))).thenThrow(new ReferentielRequestException());
        websiteConfigurationBusiness.saveWebsiteConfiguration(websiteConfiguration);
    }

    @Test(expected = ReferentielRequestException.class)
    public void saveWebsiteConfigKOSaveModel() throws ReferentielRequestException {
        Mockito.when(modelService.saveAll(Mockito.anyList())).thenThrow(new ReferentielRequestException());
        websiteConfigurationBusiness.saveWebsiteConfiguration(websiteConfiguration);

    }

    @Test(expected = ReferentielRequestException.class)
    public void saveWebsiteConfigKOSaveModelProperties() throws ReferentielRequestException {
        Mockito.when(modelPropertyService.saveAll(Mockito.anyList())).thenThrow(new ReferentielRequestException());
        websiteConfigurationBusiness.saveWebsiteConfiguration(websiteConfiguration);

    }
}
