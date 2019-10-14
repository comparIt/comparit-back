package com.pepit.service.impl;

import com.pepit.exception.ReferentielRequestException;
import com.pepit.model.WebsiteConfiguration;
import com.pepit.repository.WebsiteConfigurationRepository;
import com.pepit.service.WebsiteConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebsiteConfigurationServiceImpl implements WebsiteConfigurationService {

    private WebsiteConfigurationRepository websiteConfigurationRepository;

    @Autowired
    public WebsiteConfigurationServiceImpl(WebsiteConfigurationRepository websiteConfigurationRepository) {
        this.websiteConfigurationRepository = websiteConfigurationRepository;
    }

    public WebsiteConfiguration findOneById(Integer websitreConfigurationId) throws ReferentielRequestException {
        return websiteConfigurationRepository.findById(websitreConfigurationId).orElseThrow(ReferentielRequestException::new);
    }

    private WebsiteConfiguration save(WebsiteConfiguration websiteConfiguration) {
        return websiteConfigurationRepository.save(websiteConfiguration);
    }
}
