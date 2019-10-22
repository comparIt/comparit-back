package com.pepit.service;

import com.pepit.dto.ProductDto;

import java.util.List;
import java.util.Map;

public interface ProductService {

    List<ProductDto> search(Map<String, String> params);

}
