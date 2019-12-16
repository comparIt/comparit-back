package com.pepit.service;

import com.pepit.CompareITBackApplicationTests;
import com.pepit.constants.TypeModelPropertyEnum;
import com.pepit.exception.ReferentielRequestException;
import com.pepit.model.Model;
import com.pepit.model.ModelProperty;
import com.pepit.model.WebsiteConfiguration;
import com.pepit.repository.ModelPropertyRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class ModelPropertyServiceTest extends CompareITBackApplicationTests {

    @Autowired
    private ModelPropertyService modelPropertyService;

    @MockBean
    private ModelPropertyRepository modelPropertyRepository;

    private ModelProperty modelProperty;

    private Integer modelId;
    private List<ModelProperty> modelProperties = new ArrayList<>();


    @Before
    public void initTests() {
        initDatas();
        initMocks();
    }

    private void initDatas() {
        modelId = 1;
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
    }

    private void initMocks() {
        Mockito.when(modelPropertyRepository.saveAll(Mockito.anyList())).thenReturn(modelProperties);
        Mockito.when(modelPropertyRepository.save(Mockito.any())).thenReturn(modelProperty);
    }

    @Test
    public void SaveOK() {
        modelPropertyService.save(modelProperty);
        Mockito.verify(modelPropertyRepository, Mockito.times(1)).save(modelProperty);

    }

    @Test(expected = Exception.class)
    public void SaveKO() {
        Mockito.when(modelPropertyRepository.save(Mockito.any())).thenThrow(new Exception());
        modelPropertyService.save(modelProperty);
    }

    @Test
    public void SaveAllOk(){
        modelPropertyService.saveAll(modelProperties);
        Mockito.verify(modelPropertyRepository, Mockito.times(1)).saveAll(modelProperties);
    }

    @Test(expected = Exception.class)
    public void SaveAllKO(){
        Mockito.when(modelPropertyRepository.saveAll(Mockito.anyList())).thenThrow(new Exception());
        modelPropertyService.saveAll(modelProperties);
    }
}
