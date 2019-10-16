package com.pepit.repository;

import com.pepit.model.ProductDto;
import com.pepit.util.Query;

import java.util.List;

public interface ProductRepositoryCustom {
    List<ProductDto> testRequest(Query query);
}
