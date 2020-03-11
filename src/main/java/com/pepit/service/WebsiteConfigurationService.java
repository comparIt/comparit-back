package com.pepit.service;

import com.pepit.exception.ReferentielRequestException;
import com.pepit.model.Model;
import com.pepit.model.WebsiteConfiguration;

import java.util.List;

public interface WebsiteConfigurationService {
    WebsiteConfiguration findOneById(Integer websiteConfigurationId) throws ReferentielRequestException;

    WebsiteConfiguration save(WebsiteConfiguration websiteConfiguration);

    List<Model> getModelsOfData();
}
