package com.pepit.service.impl;

import com.pepit.dto.ProductDto;
import com.pepit.repository.ProductRepository;
import com.pepit.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepositoryCustom productRepositoryCustom;

    @Override
    public List<ProductDto> search(Map<String, String> params) {
        return productRepositoryCustom.testRequest(queryBuilder(params));
    }
}
