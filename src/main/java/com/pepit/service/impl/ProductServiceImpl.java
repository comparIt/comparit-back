package com.pepit.service.impl;

import com.pepit.dto.ProductDto;
import com.pepit.repository.ProductRepository;
import com.pepit.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<ProductDto> search(Map<String, String> params) {
        return null;
    }
}
