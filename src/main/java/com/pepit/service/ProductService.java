package com.pepit.service;

import com.pepit.model.ProductDto;

import java.util.List;
import java.util.Map;

public interface ProductService {

    List<ProductDto> search(Map<String, String> params, String order, Integer page);

}
