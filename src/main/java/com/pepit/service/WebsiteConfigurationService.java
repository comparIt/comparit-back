package com.pepit.service;

import com.pepit.exception.ReferentielRequestException;
import com.pepit.model.WebsiteConfiguration;

public interface WebsiteConfigurationService {
    WebsiteConfiguration findOneById(Integer websiteConfigurationId) throws ReferentielRequestException;

    WebsiteConfiguration save(WebsiteConfiguration websiteConfiguration);
}
