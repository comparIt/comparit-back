package com.pepit.business.impl;

import com.pepit.business.CompanyBusiness;
import com.pepit.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyBusinessImpl implements CompanyBusiness {

    private CompanyService companyService;

    @Autowired
    public CompanyBusinessImpl(CompanyService companyService) {
        this.companyService = companyService;
    }
}
