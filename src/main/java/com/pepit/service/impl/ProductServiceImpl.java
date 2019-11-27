package com.pepit.service.impl;

import com.pepit.dto.ProductDto;
import com.pepit.dto.ProductPagineDTO;
import com.pepit.util.Query;
import com.pepit.repository.ProductRepositoryCustom;
import com.pepit.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ProductServiceImpl implements ProductService {
    Long nbProductParPage = 10L;

    @Autowired
    ProductRepositoryCustom productRepositoryCustom;

    @Override
    public ProductPagineDTO search(Map<String, String> params, String order, Integer page, String supplier, String type) {
        Query query = new Query();
        query
                .addType(type)
                .addSupplier(supplier)
                .addAllCriterias(params)
                .page(page)
                .addSorting(order);
        List<ProductDto> productList = productRepositoryCustom.testRequest(query);
        ProductPagineDTO productPagine = new ProductPagineDTO();
        productPagine.setProductListOnThisPage(productList);
        Long l  =  productRepositoryCustom.count();
        Integer nbPages = (int) Math.ceil(l / nbProductParPage);
        System.out.println(nbPages);
        return productPagine;
    }



}
