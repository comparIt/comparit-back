package com.pepit.service;

import com.pepit.CompareITBackApplicationTests;
import com.pepit.repository.AlertRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

public class AlertServiceTest extends CompareITBackApplicationTests {

    @Autowired
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
