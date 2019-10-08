package com.pepit.business.impl;

import com.pepit.business.FilterBusiness;
import com.pepit.service.FilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilterBusinessImpl implements FilterBusiness {

    private FilterService filterService;

    @Autowired
    public FilterBusinessImpl(FilterService filterService) {
        this.filterService = filterService;
    }
}
