package com.pepit.business.impl;

import com.pepit.business.WebsiteConfigurationBusiness;
import com.pepit.service.WebsiteConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebsiteConfigurationBusinessImpl implements WebsiteConfigurationBusiness {

    private WebsiteConfigurationService websiteConfigurationService;

    @Autowired
    public WebsiteConfigurationBusinessImpl(WebsiteConfigurationService websiteConfigurationService) {
        this.websiteConfigurationService = websiteConfigurationService;
    }
}
