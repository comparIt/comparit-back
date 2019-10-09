package com.pepit.service.impl;

import com.pepit.repository.ModelPropertyRepository;
import com.pepit.service.ModelPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelPropertyServiceImpl implements ModelPropertyService {

    private ModelPropertyRepository modelPropertyRepository;

    @Autowired
    public ModelPropertyServiceImpl(ModelPropertyRepository modelPropertyRepository) {
        this.modelPropertyRepository = modelPropertyRepository;
    }
}
