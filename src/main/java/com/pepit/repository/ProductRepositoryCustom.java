package com.pepit.repository;

import com.pepit.dto.ProductDto;
import com.pepit.util.Query;

import java.util.List;

public interface ProductRepositoryCustom {
    List<ProductDto> testRequest(Query query);
}
