package com.pepit.service;

import com.pepit.dto.ProductDto;
import com.pepit.dto.ProductPagineDTO;

import java.util.Map;

public interface ProductService {

    ProductPagineDTO search(Map<String, String> params, String order, Integer page, String supplier, String type);

    ProductDto searchProductById(String productId);

    long count();
}
