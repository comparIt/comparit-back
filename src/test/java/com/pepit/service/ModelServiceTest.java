package com.pepit.service;

import com.pepit.CompareITBackApplicationTests;
import com.pepit.exception.ReferentielRequestException;
import com.pepit.model.Model;
import com.pepit.repository.ModelRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;


public class ModelServiceTest extends CompareITBackApplicationTests {

    @Autowired
    private ModelService modelService;

    @MockBean
    private ModelRepository modelRepository;

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
    }

    private void initMocks() {
        Mockito.when(modelRepository.save(Mockito.any(Model.class))).thenReturn(model);
        Mockito.when(modelRepository.saveAll(Mockito.anyList())).thenReturn(modelList);
    }


    @Test
    public void saveOk() {
        Assert.assertEquals(model, modelService.save(model));
    }

   @Test(expected = Exception.class)
    public void saveKO() {
        Mockito.when(modelService.save(model)).thenThrow(new Exception());
        modelService.save(model);
    }

   @Test
    public void saveAllOk(){
        Assert.assertEquals(modelList,modelService.saveAll(modelList));
    }

   @Test(expected = Exception.class)
    public void saveAllKo(){
        Mockito.when(modelService.saveAll(modelList)).thenThrow(new Exception());
        modelService.saveAll(modelList);
    }
}
