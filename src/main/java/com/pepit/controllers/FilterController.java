package com.pepit.controllers;

import com.pepit.business.FilterBusiness;
import com.pepit.service.FilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "filter", produces = MediaType.APPLICATION_JSON_VALUE)
public class FilterController {

    private FilterBusiness filterBusiness;
    private FilterService filterService;

    @Autowired
    public FilterController(FilterBusiness filterBusiness, FilterService filterService) {
        this.filterBusiness = filterBusiness;
        this.filterService = filterService;
    }
}
