package com.pepit.service.impl;

import com.pepit.repository.FilterRepository;
import com.pepit.service.FilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilterServiceImpl implements FilterService {

    private FilterRepository filterRepository;

    @Autowired
    public FilterServiceImpl(FilterRepository filterRepository) {
        this.filterRepository = filterRepository;
    }
}
