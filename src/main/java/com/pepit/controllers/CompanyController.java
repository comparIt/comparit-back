package com.pepit.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.pepit.business.CompanyBusiness;
import com.pepit.model.Product;
import com.pepit.model.ProductDto;
import com.pepit.repository.ProductRepository;
import com.pepit.service.CompanyService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.objenesis.SpringObjenesis;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

@RestController
@RequestMapping(value = "company", produces = MediaType.APPLICATION_JSON_VALUE)
public class CompanyController {

    private CompanyBusiness companyBusiness;
    private CompanyService companyService;

    @Autowired
    public CompanyController(CompanyBusiness companyBusiness, CompanyService companyService, ProductRepository productRepository) {
        this.companyBusiness = companyBusiness;
        this.companyService = companyService;
    }

    @PostMapping(value = "/")
    public ResponseEntity<ProductDto> post(@RequestBody ProductDto product) {

        if (product != null) {
            System.out.println(product.getProperties());
        }

        // TODO: call persistence layer to update
        return new ResponseEntity<ProductDto>(product, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/byUrl")
    @ResponseBody
    public String sendUrlToGet(@RequestParam("url") String url, @RequestParam("fournId") String fournId, @RequestParam("typeProduct") String typeProduct){
        System.out.println(fournId + typeProduct + url);
        //TODO S'assurer que typeProduct est coherent avec un des Model existant
        return companyService.getFromUrl(url, fournId, typeProduct);
    }
}
