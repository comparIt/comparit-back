package com.pepit.service.impl;

import com.pepit.model.ProductDto;
import com.pepit.util.Query;
import com.pepit.repository.ProductRepositoryCustom;
import com.pepit.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepositoryCustom productRepositoryCustom;

    @Override
    public List<ProductDto> search(Map<String, String> params, String order, Integer page, String supplier, String type) {
        Query query = new Query();
        query
                .addType(type)
                .addSupplier(supplier)
                .addAllCriterias(params)
                .page(page)
                .addSorting(order);
        return productRepositoryCustom.testRequest(query);
    }



}
