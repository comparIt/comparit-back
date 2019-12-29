package com.pepit.service.impl;

import com.pepit.controllers.FilterController;
import com.pepit.converters.FilterConverter;
import com.pepit.dto.FilterDto;
import com.pepit.model.Filter;
import com.pepit.model.User;
import com.pepit.repository.FilterRepository;
import com.pepit.service.FilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilterServiceImpl implements FilterService {

    @Autowired
    FilterRepository filterRepository;

    @Autowired
    FilterConverter filterConverter;


    @Override
    public FilterDto create(Filter filter) {
        return filterConverter.entityToDto(filterRepository.save(filter));
    }

    @Override
    public List<Filter> getAllFiltersByUser(User userByToken) {
        return filterRepository.findAllByUsers(userByToken);
    }

    @Override
    public List<Filter> getAllFilterWithAlertActivatedAndDaily() {
        return filterRepository.findAllByIsAlertIsTrueAndDaily();
    }

    @Override
    public List<Filter> getAllFilterWithAlertActivatedAndWeekly() {
        return filterRepository.findAllByIsAlertIsTrueAndWeekly();
    }

    @Override
    public List<Filter> getAllFilterWithAlertActivatedAndMonthly() {
        return filterRepository.findAllByIsAlertIsTrueAndMonthly();
    }
}
