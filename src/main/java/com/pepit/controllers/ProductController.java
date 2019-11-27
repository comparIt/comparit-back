package com.pepit.controllers;


import com.pepit.constants.Routes;
import com.pepit.dto.ProductPagineDTO;
import com.pepit.model.Product;
import com.pepit.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = Routes.PRODUCT, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @CrossOrigin
    @GetMapping(value = "/search")
    public ResponseEntity<ProductPagineDTO> search(@RequestParam @Nullable Map<String, String> params,
                                                   @RequestParam @Nullable String order,
                                                   @RequestParam @Nullable Integer page,
                                                   @RequestParam @Nullable String supplier,
                                                   @RequestParam @Nullable String type
                                                   ){
        return ResponseEntity.status(200).body(productService.search(params, order, page, supplier, type));
    }

    @CrossOrigin
    @GetMapping("/{productId}")
    public ResponseEntity<Product> searchProduct(@PathVariable Integer productId){
        return null;
    }




}
