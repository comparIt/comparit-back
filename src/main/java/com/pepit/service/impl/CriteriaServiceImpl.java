package com.pepit.service.impl;

import com.pepit.repository.CriteriaRepository;
import com.pepit.service.CriteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CriteriaServiceImpl implements CriteriaService {

    private CriteriaRepository criteriaRepository;

    @Autowired
    public CriteriaServiceImpl(CriteriaRepository criteriaRepository) {
        this.criteriaRepository = criteriaRepository;
    }
}
