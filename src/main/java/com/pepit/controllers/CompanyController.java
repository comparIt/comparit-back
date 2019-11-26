package com.pepit.controllers;

import com.pepit.business.CompanyBusiness;
import com.pepit.constants.Routes;
import com.pepit.model.Model;
import com.pepit.service.CompanyService;
import com.pepit.service.WebsiteConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(Routes.COMPANY)
public class CompanyController {

    private CompanyBusiness companyBusiness;
    private CompanyService companyService;
    private WebsiteConfigurationService websiteConfigurationService;

    @Autowired
    public CompanyController(CompanyBusiness companyBusiness, CompanyService companyService, WebsiteConfigurationService websiteConfigurationService) {
        this.companyBusiness = companyBusiness;
        this.companyService = companyService;
        this.websiteConfigurationService = websiteConfigurationService;
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
        List<Model> currentModel = websiteConfigurationService.findOneById(1).getModelByTechnicalName(typeProduit);
        return new ResponseEntity<String>(companyService.fromCsvToDb(file, supplierId.replace("\"", ""), typeProduit.replace("\"", "")), HttpStatus.OK);
    }
}
