package com.pepit.controllers;

import com.pepit.business.CompanyBusiness;
import com.pepit.model.ProductDto;
import com.pepit.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "company", produces = MediaType.APPLICATION_JSON_VALUE)
public class CompanyController {

    private CompanyBusiness companyBusiness;
    private CompanyService companyService;

    @Autowired
    public CompanyController(CompanyBusiness companyBusiness, CompanyService companyService) {
        this.companyBusiness = companyBusiness;
        this.companyService = companyService;
    }

    @PostMapping(value = "/")
    public ResponseEntity<ProductDto> post(@RequestBody ProductDto product) {

        if (product != null) {
            System.out.println(product.getDetails());
        }

        // TODO: call persistence layer to update
        return new ResponseEntity<ProductDto>(product, HttpStatus.OK);
    }



    @CrossOrigin
    @GetMapping("/byUrl")
    @ResponseBody
    public String sendUrlToGet(@RequestParam("url") String url){
        System.out.println(url);
        return "OK";
    }
}
