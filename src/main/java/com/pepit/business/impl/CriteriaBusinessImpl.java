package com.pepit.business.impl;

import com.pepit.business.CriteriaBusiness;
import com.pepit.service.CriteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CriteriaBusinessImpl implements CriteriaBusiness {

    private CriteriaService criteriaService;

    @Autowired
    public CriteriaBusinessImpl(CriteriaService criteriaService) {
        this.criteriaService = criteriaService;
    }
}
