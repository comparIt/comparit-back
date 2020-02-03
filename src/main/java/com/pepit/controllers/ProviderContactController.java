package com.pepit.controllers;

import com.pepit.constants.Routes;
import com.pepit.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Routes.PROVIDER_CONTACT)
public class ProviderContactController {
    ProviderService providerService;

    @Autowired
    public ProviderContactController(ProviderService providerService) {
        this.providerService = providerService;
    }
}
