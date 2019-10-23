package com.pepit.controllers;

import com.pepit.business.ModelBusiness;
import com.pepit.constants.Routes;
import com.pepit.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Routes.MODEL)
public class ModelController {

    private ModelBusiness modelBusiness;
    private ModelService modelService;

    @Autowired
    public ModelController(ModelBusiness modelBusiness, ModelService modelService) {
        this.modelBusiness = modelBusiness;
        this.modelService = modelService;
    }
}
