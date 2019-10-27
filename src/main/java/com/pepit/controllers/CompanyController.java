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

import java.io.InputStream;
import java.util.logging.Logger;

@RestController
@RequestMapping(Routes.COMPAGNY)
public class CompanyController {

    private CompanyBusiness companyBusiness;
    private CompanyService companyService;
    private static final Logger logger = Logger.getLogger(CompanyController.class.getName());

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
        return new ResponseEntity<String>(companyService.fromUrlToDb(url, supplierId, type), HttpStatus.OK);
    }


    @PostMapping("/byCsvUpload")
    public ResponseEntity<String> uploadData(@RequestParam("file") MultipartFile file, @RequestParam("supplierId") String supplierId, @RequestParam("type") String type) throws Exception {
        if (file == null) {
            throw new RuntimeException("You must select a file for uploading");
        }
        InputStream inputStream = file.getInputStream();
        String originalName = file.getOriginalFilename();
        String name = file.getName();
        String contentType = file.getContentType();
        long size = file.getSize();
        logger.info("inputStream: " + inputStream);
        logger.info("originalName: " + originalName);
        logger.info("name: " + name);
        logger.info("contentType: " + contentType);
        logger.info("size: " + size);

        return new ResponseEntity<String>(companyService.fromCsvToDb(inputStream, supplierId, type), HttpStatus.OK);
    }
}
