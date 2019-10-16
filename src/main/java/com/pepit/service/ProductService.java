package com.pepit.service;

import com.pepit.model.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {

    List<Product> search(Map<String, String> params);

}
