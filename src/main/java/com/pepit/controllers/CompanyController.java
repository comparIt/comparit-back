package com.pepit.controllers;

import com.pepit.business.CompanyBusiness;
import com.pepit.constants.Routes;
import com.pepit.repository.ProductRepository;
import com.pepit.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(Routes.COMPAGNY)
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
    @GetMapping("/byUrl/{typeProduit}")
    @ResponseBody
    public ResponseEntity<String> sendUrlToGet(@RequestParam("url") String url, @PathVariable("typeProduit") String typeProduit) {
        //TODO S'assurer que type reçu est coherent avec un des Model existant
        //TODO MAsquer le resultat qd c'est conforme result http only

        //TODO a recuperer a partir du token
        String supplierId = "1";
        return new ResponseEntity<String>(companyService.fromUrlToDb(url, supplierId.replace("\"", ""), typeProduit.replace("\"", "")), HttpStatus.OK);
    }


    @PostMapping("/byCsvUpload/{typeProduit}")
    public ResponseEntity<String> uploadData(@RequestParam("files") MultipartFile file, @PathVariable("typeProduit") String typeProduit) throws Exception {

        //TODO a recuperer a partir du token
        String supplierId = "1";
        String type = typeProduit;
        return new ResponseEntity<String>(companyService.fromCsvToDb(file, supplierId.replace("\"", ""), type.replace("\"", "")), HttpStatus.OK);
    }
}
