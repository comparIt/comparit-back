package com.pepit.service;

import com.pepit.dto.ProductPagineDTO;
import com.pepit.model.Product;

import java.util.Map;

public interface ProductService {

    ProductPagineDTO search(Map<String, String> params, String order, Integer page, String supplier, String type);

    Product searchProductById(String productId);
}
