package com.pepit.repository;

import com.pepit.model.ProductDto;

import java.util.List;

public interface ProductRepositoryCustom {
    List<ProductDto> testRequest(String query);
}
