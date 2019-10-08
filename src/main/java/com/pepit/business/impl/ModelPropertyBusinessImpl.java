package com.pepit.business.impl;

import com.pepit.business.ModelPropertyBusiness;
import com.pepit.service.ModelPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelPropertyBusinessImpl implements ModelPropertyBusiness {

    private ModelPropertyService modelPropertyService;

    @Autowired
    public ModelPropertyBusinessImpl(ModelPropertyService modelPropertyService) {
        this.modelPropertyService = modelPropertyService;
    }
}
