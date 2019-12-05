package com.pepit.service;

import com.pepit.CompareITBackApplicationTests;
import com.pepit.dto.ProductDto;
import com.pepit.dto.ProductPagineDTO;
import com.pepit.repository.ProductRepositoryCustom;
import com.pepit.util.Query;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertTrue;

public class ProductServiceTest extends CompareITBackApplicationTests {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepositoryCustom productRepository;

    @MockBean
    private ProductRepositoryCustom productRepositoryCustom;

    private ProductPagineDTO productPagineDTO;

    private List<ProductDto> productDtoList;

    private Map<String,Object> mapProperties;

    private Query query;


    @Before
    public void initTests() {
        initDatas();
        initMocks();
    }

    private void initDatas() {
        productDtoList = new ArrayList<>();
        mapProperties = new HashMap<>();
        mapProperties.put("doors",2);

        ProductDto product = new ProductDto();
        product.set_id("1");
        product.setSupplierId("1");
        product.setType("cars");
        product.setProperties(mapProperties);

        productDtoList.add(product);

        productPagineDTO = ProductPagineDTO.builder()
                .nbPagesTotal((int)Math.ceil(productDtoList.size()/10))
                .pageActuelle(1)
                .productsToDisplay(productDtoList)
                .build();

        query = new Query();
    }

    private void initMocks() {
        Mockito.when(productRepositoryCustom.testRequest(Mockito.any(Query.class))).thenReturn(productDtoList);
    }

    @Test
    public void testSample() {
        assertTrue(true);
    }

    /**
     * List<ProductDto> search(Map<String, String> params);
     */

    @Test
    public void searchOK(){

    }

    @Test
    public void searchKO(){

    }

    @Test
    public void searchOkButNoPage(){

    }

    @Test
    public void searchOkPageIncoherente(){

    }

    @Test
    public void searchButNoType(){

    }

    @Test
    public void searchButNoSupplier(){

    }

    @Test
    public void searchButNoSorting(){

    }
}
