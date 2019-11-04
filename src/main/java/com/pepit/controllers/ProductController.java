package com.pepit.controllers;


import com.pepit.dto.ProductDto;
import com.pepit.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "product", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @CrossOrigin
    @GetMapping(value = "/search")
    public ResponseEntity<List<ProductDto>> search(@RequestParam @Nullable Map<String, String> params,
                                                   @RequestParam @Nullable String order,
                                                   @RequestParam @Nullable Integer page,
                                                   @RequestParam @Nullable String supplier,
                                                   @RequestParam @Nullable String type
                                                   ){
        return ResponseEntity.status(200).body(productService.search(params, order, page, supplier, type));
    }

}
