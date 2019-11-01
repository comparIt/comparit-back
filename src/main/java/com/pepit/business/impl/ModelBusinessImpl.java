package com.pepit.business.impl;

import com.pepit.business.ModelBusiness;
import com.pepit.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelBusinessImpl implements ModelBusiness {

    private ModelService modelService;

    @Autowired
    public ModelBusinessImpl(ModelService modelService) {
        this.modelService = modelService;
    }
}
