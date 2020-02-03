package com.pepit.service.impl;

import com.pepit.repository.ProviderContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProviderServiceImpl {

    private ProviderContactRepository providerContactRepository;

    @Autowired
    public ProviderServiceImpl(ProviderContactRepository providerContactRepository) {
        this.providerContactRepository = providerContactRepository;
    }
}
