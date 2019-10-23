package com.pepit.business.impl;

import com.pepit.business.WebsiteConfigurationBusiness;
import com.pepit.exception.ReferentielRequestException;
import com.pepit.model.Model;
import com.pepit.model.WebsiteConfiguration;
import com.pepit.service.ModelService;
import com.pepit.service.WebsiteConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebsiteConfigurationBusinessImpl implements WebsiteConfigurationBusiness {

    private WebsiteConfigurationService websiteConfigurationService;
    private ModelService modelService;

    @Autowired
    public WebsiteConfigurationBusinessImpl(WebsiteConfigurationService websiteConfigurationService, ModelService modelService) {
        this.websiteConfigurationService = websiteConfigurationService;
        this.modelService = modelService;
    }

    @Override
    public WebsiteConfiguration saveWebsiteConfiguration(WebsiteConfiguration websiteConfiguration) throws ReferentielRequestException {
        try{
            WebsiteConfiguration save = websiteConfigurationService.save(websiteConfiguration);
            save.getModelList().forEach(modelService::save);
        }catch (Exception e){
            throw new ReferentielRequestException();
        }
        return websiteConfiguration;
    }
}
