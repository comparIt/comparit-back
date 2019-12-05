package com.pepit.service;

import com.pepit.CompareITBackApplicationTests;
import com.pepit.exception.ReferentielRequestException;
import com.pepit.model.Model;
import com.pepit.model.WebsiteConfiguration;
import com.pepit.repository.CompanyRepository;
import com.pepit.repository.ProductRepositoryCustom;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class CompanyServiceTest extends CompareITBackApplicationTests {

    @Autowired
    private CompanyService companyService;

    @MockBean
    private CompanyRepository companyRepository;

    @MockBean
    private WebsiteConfigurationService websiteConfigurationService;

    @MockBean
    private RestTemplate restTemplate;

    @MockBean
    private ProductRepositoryCustom productRepositoryCustom;

    ResponseEntity responseEntity;
    WebsiteConfiguration websiteConfiguration;
    Model model;
    Integer modelId;
    List<Model> modelList;
    Integer websiteConfigurationId;

    @Before
    public void initTests() throws ReferentielRequestException {
        initDatas();
        initMocks();
    }

    private void initDatas() {
        responseEntity = new ResponseEntity(HttpStatus.OK);
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

    private void initMocks() throws ReferentielRequestException {
        Mockito.when(restTemplate.exchange(Mockito.anyString(), HttpMethod.GET, Mockito.any(HttpEntity.class), String.class)).thenReturn(null);
        Mockito.when(websiteConfigurationService.findOneById(Mockito.anyInt())).thenReturn(websiteConfiguration);
    }

    @Test
    public void testSample() {
        assertTrue(true);
    }

    @Test
    public void fromUrlToDbOk() {

    }

    @Test(expected = Exception.class)
    public void fromUrlToDbKORestemplate() {

    }

    @Test(expected = Exception.class)
    public void fromUrlToDbKORemoveDoc() {

    }

    @Test(expected = Exception.class)
    public void fromUrlToDbKOAddDoc() {

    }

    @Test
    public void fromCsvToDbOK() {

    }

    @Test(expected = Exception.class)
    public void fromCsvToDbKORemoveDoc() {

    }

    @Test(expected = Exception.class)
    public void fromCsvToDbKOAddDoc() {

    }

    @Test(expected = ReferentielRequestException.class)
    public void recalculMinMaxValueKOWebsiteConfiguration() {

    }

    @Test(expected = ReferentielRequestException.class)
    public void recalculMinMaxValueKOProductRepositoryUpdateBornes() {

    }


    @Test(expected = ReferentielRequestException.class)
    public void recalculMinMaxValueKOProductRepositoryListeDistinct() {

    }

    @Test(expected = ReferentielRequestException.class)
    public void recalculMinMaxValueKOWebsiteConfigurationSave() {

    }

    @Test(expected = Exception.class)
    public void compareModelWithFileHeaderKOFindOneById() {

    }

    @Test(expected = Exception.class)
    public void compareModelWithFileInputException() {

    }


}
