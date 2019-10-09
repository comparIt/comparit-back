package com.pepit.business.impl;

import com.pepit.business.AlertBusiness;
import com.pepit.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlertBusinessImpl implements AlertBusiness {

    private AlertService alertService;

    @Autowired
    public AlertBusinessImpl(AlertService alertService) {
        this.alertService = alertService;
    }
}
