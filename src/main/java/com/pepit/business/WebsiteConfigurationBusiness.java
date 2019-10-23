package com.pepit.business;

import com.pepit.exception.ReferentielRequestException;
import com.pepit.model.WebsiteConfiguration;

public interface WebsiteConfigurationBusiness {
    WebsiteConfiguration saveWebsiteConfiguration(WebsiteConfiguration websiteConfiguration) throws ReferentielRequestException;
}
