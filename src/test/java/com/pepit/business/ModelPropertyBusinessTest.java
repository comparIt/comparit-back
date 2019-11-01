package com.pepit.business;

import com.pepit.repository.ModelPropertyRepository;
import com.pepit.service.ModelPropertyService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ModelPropertyBusiness.class)
public class ModelPropertyBusinessTest {

    @Autowired
    private ModelPropertyBusiness modelPropertyBusiness;

    @MockBean
    private ModelPropertyService modelPropertyService;

    @MockBean
    private ModelPropertyRepository modelPropertyRepository;

    @Before
    public void initTests() {
        initDatas();
        initMocks();
    }

    private void initDatas() {

    }

    private void initMocks() {

    }

    @Test
    public void testSample() {
        assertTrue(true);
    }
}
