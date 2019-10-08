package com.pepit.controllers;

import com.pepit.business.CompagnyBusiness;
import com.pepit.service.CompagnyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "compagny", produces = MediaType.APPLICATION_JSON_VALUE)
public class CompagnyController {

    private CompagnyBusiness compagnyBusiness;
    private CompagnyService compagnyService;

    @Autowired
    public CompagnyController(CompagnyBusiness compagnyBusiness, CompagnyService compagnyService) {
        this.compagnyBusiness = compagnyBusiness;
        this.compagnyService = compagnyService;
    }
}
