package com.pepit.business;

import com.pepit.CompareITBackApplicationTests;
import com.pepit.repository.FilterRepository;
import com.pepit.service.FilterService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

public class FilterBusinessTest extends CompareITBackApplicationTests {

    @Autowired
    private FilterBusiness filterBusiness;

    @MockBean
    private FilterService filterService;

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
