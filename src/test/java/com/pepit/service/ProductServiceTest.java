package com.pepit.service;

import com.pepit.CompareITBackApplicationTests;
import com.pepit.dto.ProductDto;
import com.pepit.dto.ProductPagineDTO;
import com.pepit.repository.ProductRepositoryCustom;
import com.pepit.util.Query;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductServiceTest extends CompareITBackApplicationTests {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepositoryCustom productRepositoryCustom;

    private ProductPagineDTO productPagineDTOAll;
    private ProductPagineDTO productPagineDTOSuplier1;
    private ProductPagineDTO productPagineDTOTypeCars;

    private List<ProductDto> productDtoListAll;
    private List<ProductDto> productDtoListSupplier1;
    private List<ProductDto> productDtoListTypeCars;

    private Map<String,Object> mapProperties;

    private Query queryAll;
    private Query querySuppliers1;
    private Query queryTypeCars;
    private Query queryPage1000;
    private Query queryNoPage;


    @Before
    public void initTests() {
        initDatas();
        initMocks();
    }

    private void initDatas() {
        productDtoListAll = new ArrayList<>();
        productDtoListSupplier1 = new ArrayList<>();
        productDtoListTypeCars = new ArrayList<>();

        mapProperties = new HashMap<>();
        mapProperties.put("doors",2);

        ProductDto productAll = new ProductDto();
        productAll.set_id("1");
        productAll.setSupplierId("1");
        productAll.setType("cars");
        productAll.setProperties(mapProperties);

        productDtoListAll.add(productAll);
        productDtoListSupplier1.add(productAll);
        productDtoListTypeCars.add(productAll);


        ProductDto productSupplier2 = new ProductDto();
        productAll.set_id("2");
        productAll.setSupplierId("2");
        productAll.setType("cars");
        productAll.setProperties(mapProperties);

        productDtoListTypeCars.add(productSupplier2);
        productDtoListAll.add(productSupplier2);

        ProductDto productTypeAutre = new ProductDto();
        productAll.set_id("3");
        productAll.setSupplierId("1");
        productAll.setType("phone");
        productAll.setProperties(mapProperties);

        productDtoListAll.add(productTypeAutre);
        productDtoListSupplier1.add(productTypeAutre);

        productPagineDTOAll = ProductPagineDTO.builder()
                .nbPagesTotal(1)
                .pageActuelle(1)
                .productsToDisplay(productDtoListAll)
                .build();

        productPagineDTOSuplier1 = ProductPagineDTO.builder()
                .nbPagesTotal(1)
                .pageActuelle(1)
                .productsToDisplay(productDtoListSupplier1)
                .build();

        productPagineDTOTypeCars = ProductPagineDTO.builder()
                .nbPagesTotal(1)
                .pageActuelle(1)
                .productsToDisplay(productDtoListTypeCars)
                .build();

        queryAll = new Query();
        querySuppliers1 = new Query();
        queryTypeCars = new Query();
        queryNoPage = new Query();
        queryPage1000 = new Query();

        queryAll.addType("cars");
        queryAll.addSupplier("1");
        queryAll.addAllCriterias(new HashMap<>());
        queryAll.page(1);

        queryNoPage.addType("cars");
        queryNoPage.addSupplier("1");
        queryNoPage.addAllCriterias(new HashMap<>());
        queryNoPage.page(null);

        queryPage1000.addType("cars");
        queryPage1000.addSupplier("1");
        queryPage1000.addAllCriterias(new HashMap<>());
        queryPage1000.page(1000);

        queryTypeCars.addType("cars");
        queryTypeCars.addAllCriterias(new HashMap<>());
        queryTypeCars.page(1);

        querySuppliers1.addSupplier("1");
        querySuppliers1.addAllCriterias(new HashMap<>());
        querySuppliers1.page(1);
    }

    private void initMocks() {
    }

    @Test
    public void searchOK(){
        Mockito.when(productRepositoryCustom.searchRequest(Mockito.any(Query.class))).thenReturn(productDtoListAll);
        Assert.assertEquals(productPagineDTOAll, productService.search(new HashMap<>(), null, 1, "1", "cars"));
    }

    @Test(expected = Exception.class)
    public void searchKO(){
        Mockito.when(productRepositoryCustom.searchRequest(Mockito.any(Query.class))).thenThrow(new Exception());
    }

    @Test
    public void searchOkButNoPage(){
        Mockito.when(productRepositoryCustom.searchRequest(Mockito.any(Query.class))).thenReturn(productDtoListAll);
        Assert.assertEquals(productPagineDTOAll, productService.search(new HashMap<>(), null, null, "1", "cars"));
    }

    @Test
    public void searchOkPageIncoherente(){
        Mockito.when(productRepositoryCustom.searchRequest(Mockito.any(Query.class))).thenReturn(productDtoListAll);
        Assert.assertEquals(productPagineDTOAll, productService.search(new HashMap<>(), null, 1000, "1", "cars"));
    }

    @Test
    public void searchSupplier1(){
        Mockito.when(productRepositoryCustom.searchRequest(Mockito.any(Query.class))).thenReturn(productDtoListSupplier1);
        Assert.assertEquals(productPagineDTOSuplier1, productService.search(new HashMap<>(), null, 1, "1", null));
    }

    @Test
    public void searchTypeCars(){
        Mockito.when(productRepositoryCustom.searchRequest(Mockito.any(Query.class))).thenReturn(productDtoListTypeCars);
        Assert.assertEquals(productPagineDTOTypeCars, productService.search(new HashMap<>(), null, 1, null, "cars"));
    }
}
