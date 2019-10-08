package com.pepit.controllers;

import com.pepit.business.CriteriaBusiness;
import com.pepit.service.CriteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "criteria", produces = MediaType.APPLICATION_JSON_VALUE)
public class CriteriaController {

    private CriteriaBusiness criteriaBusiness;
    private CriteriaService criteriaService;

    @Autowired
    public CriteriaController(CriteriaBusiness criteriaBusiness, CriteriaService criteriaService) {
        this.criteriaBusiness = criteriaBusiness;
        this.criteriaService = criteriaService;
    }
}
