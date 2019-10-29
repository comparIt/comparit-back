package com.pepit.controllers;

import com.pepit.business.AlertBusiness;
import com.pepit.constants.Routes;
import com.pepit.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Routes.ALERT)
public class AlertController {

    private AlertBusiness alertBusiness;
    private AlertService alertService;

    @Autowired
    public AlertController(AlertBusiness alertBusiness, AlertService alertService) {
        this.alertBusiness = alertBusiness;
        this.alertService = alertService;
    }
}