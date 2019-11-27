package com.pepit.service;

import com.pepit.CompareITBackApplicationTests;
import com.pepit.repository.ProductRepositoryCustom;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static junit.framework.TestCase.assertTrue;

public class ProductServiceTest extends CompareITBackApplicationTests {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepositoryCustom productRepository;

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

    /**
     * List<ProductDto> search(Map<String, String> params);
     */
}
