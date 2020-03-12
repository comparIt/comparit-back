package com.pepit.controllers;

import com.pepit.constants.Routes;
import com.pepit.dto.CompanyDto;
import com.pepit.dto.SupplierDto;
import com.pepit.dto.UserDto;
import com.pepit.model.Model;
import com.pepit.service.CompanyService;
import com.pepit.service.UserService;
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

    @Autowired
    private CompanyService companyService;
    @Autowired
    private UserService userService;
    @Autowired
    private WebsiteConfigurationService websiteConfigurationService;

    @GetMapping("/byUrl/{typeProduit}")
    @ResponseBody
    public ResponseEntity<String> sendUrlToGet(@RequestParam("url") String url, @PathVariable("typeProduit") String typeProduit) {
        //TODO S'assurer que type reçu est coherent avec un des Model existant
        //TODO MAsquer le resultat qd c'est conforme result http only

        String supplierId = userService.getUserByToken().getId().toString();
        return new ResponseEntity<String>(companyService.fromUrlToDb(url, supplierId.replace("\"", ""), typeProduit.replace("\"", "")), HttpStatus.OK);
    }

    @PostMapping("/byCsvUpload/{typeProduit}")
    public ResponseEntity<String> uploadData(@RequestParam("files") MultipartFile file, @PathVariable("typeProduit") String typeProduit) throws Exception {
        String supplierId = userService.getUserByToken().getId().toString();
        return new ResponseEntity<String>(companyService.fromCsvToDb(file, supplierId.replace("\"", ""), typeProduit.replace("\"", "")), HttpStatus.OK);
    }

    @PutMapping("/addCompagny")
    public ResponseEntity<CompanyDto> createCompany(@RequestBody CompanyDto companyDto) {
        return ResponseEntity.status(200).body(companyService.create(companyDto));
    }

    @PutMapping("/supplier")
    public ResponseEntity<UserDto> createSupplier(@RequestBody SupplierDto supplierDto) {
        return ResponseEntity.status(200).body(userService.createSupplier(supplierDto));
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Model>> getCategories() {
        return ResponseEntity.status(200).body(websiteConfigurationService.getModelsOfData());
    }

}
