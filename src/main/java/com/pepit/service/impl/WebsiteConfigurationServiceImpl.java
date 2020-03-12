package com.pepit.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pepit.exception.ReferentielRequestException;
import com.pepit.model.Model;
import com.pepit.model.WebsiteConfiguration;
import com.pepit.repository.WebsiteConfigurationRepository;
import com.pepit.service.WebsiteConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebsiteConfigurationServiceImpl implements WebsiteConfigurationService {

    private WebsiteConfigurationRepository websiteConfigurationRepository;

    @Autowired
    public WebsiteConfigurationServiceImpl(WebsiteConfigurationRepository websiteConfigurationRepository) {
        this.websiteConfigurationRepository = websiteConfigurationRepository;
    }

    @Override
    public WebsiteConfiguration findOneById(Integer websiteConfiguration) throws ReferentielRequestException {
        return websiteConfigurationRepository.findById(websiteConfiguration).orElseThrow(ReferentielRequestException::new);
    }

    @Override
    public WebsiteConfiguration save(WebsiteConfiguration websiteConfiguration) {
        return websiteConfigurationRepository.save(websiteConfiguration);
    }

    @Override
    public List<Model> getModelsOfData() {
        return websiteConfigurationRepository.findById(1).get().getModels();
    }
}
