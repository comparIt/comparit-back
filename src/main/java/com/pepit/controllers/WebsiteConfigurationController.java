package com.pepit.controllers;

import com.pepit.business.WebsiteConfigurationBusiness;
import com.pepit.exception.ReferentielRequestException;
import com.pepit.model.WebsiteConfiguration;
import com.pepit.service.WebsiteConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "websiteconfig", produces = MediaType.APPLICATION_JSON_VALUE)
public class WebsiteConfigurationController {

    private WebsiteConfigurationService websiteConfigurationService;
    private WebsiteConfigurationBusiness websiteConfigurationBusiness;

    @Autowired
    public WebsiteConfigurationController(WebsiteConfigurationService websiteConfigurationService, WebsiteConfigurationBusiness websiteConfigurationBusiness) {
        this.websiteConfigurationService = websiteConfigurationService;
        this.websiteConfigurationBusiness = websiteConfigurationBusiness;
    }

    @CrossOrigin
    @GetMapping("/get/{websiteConfigurationId}")
    @ResponseBody
    public ResponseEntity<WebsiteConfiguration> getWebsiteConfigurationById(@PathVariable("websiteConfigurationId") Integer websiteConfigurationId) throws ReferentielRequestException {
        return ResponseEntity.status(200).body(websiteConfigurationService.findOneById(websiteConfigurationId));
    }
}
