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

    @CrossOrigin
    @GetMapping("/byUrl")
    @ResponseBody
    public ResponseEntity<String> sendUrlToGet(@RequestParam("url") String url, @RequestParam("supplierId") String supplierId, @RequestParam("type") String type){
        //TODO S'assurer que type reçu est coherent avec un des Model existant
        //TODO MAsquer le resultat qd c'est conforme result http only
        return new ResponseEntity<String>(companyService.fromUrlToDb(url, supplierId.replace("\"", ""), type.replace("\"", "")), HttpStatus.OK);
    }


    @PostMapping("/byCsvUpload/{typeProduit}")
    public ResponseEntity<String> uploadData(@RequestParam("files") MultipartFile file, @PathVariable("typeProduit") String typeProduit) throws Exception {
        String supplierId = "1";
        String type = typeProduit;
        return new ResponseEntity<String>(companyService.fromCsvToDb(file, supplierId.replace("\"", ""), type.replace("\"", "")), HttpStatus.OK);
    }
}
