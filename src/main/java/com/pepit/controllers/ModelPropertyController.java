package com.pepit.controllers;

import com.pepit.business.ModelPropertyBusiness;
import com.pepit.constants.Routes;
import com.pepit.service.ModelPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Routes.MODEL_PROPERTY)
public class ModelPropertyController {

    private ModelPropertyBusiness modelPropertyBusiness;
    private ModelPropertyService modelPropertyService;

    @Autowired
    public ModelPropertyController(ModelPropertyBusiness modelPropertyBusiness, ModelPropertyService modelPropertyService) {
        this.modelPropertyBusiness = modelPropertyBusiness;
        this.modelPropertyService = modelPropertyService;
    }
}
