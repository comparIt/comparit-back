package com.pepit.service.impl;

import com.pepit.repository.CompagnyRepository;
import com.pepit.service.CompagnyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompagnyServiceImpl implements CompagnyService {

    private CompagnyRepository compagnyRepository;

    @Autowired
    public CompagnyServiceImpl(CompagnyRepository compagnyRepository) {
        this.compagnyRepository = compagnyRepository;
    }
}
