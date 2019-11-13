package com.pepit.business;

import com.pepit.CompareITBackApplicationTests;
import com.pepit.repository.AlertRepository;
import com.pepit.service.AlertService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.Assert.assertTrue;

public class AlertBusinessTest extends CompareITBackApplicationTests {

    @Autowired
    private AlertBusiness alertBusiness;

    @MockBean
    private AlertService alertService;

    @MockBean
    private AlertRepository alertRepository;

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
