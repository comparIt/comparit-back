package com.pepit.business.impl;

import com.pepit.business.CompagnyBusiness;
import com.pepit.service.CompagnyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompagnyBusinessImpl implements CompagnyBusiness {

    private CompagnyService compagnyService;

    @Autowired
    public CompagnyBusinessImpl(CompagnyService compagnyService) {
        this.compagnyService = compagnyService;
    }
}
