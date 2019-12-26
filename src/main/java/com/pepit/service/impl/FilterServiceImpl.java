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
    public List<FilterDto> getAllFiltersByUser(User userByToken) {
        return filterConverter.entityListToDtoList(filterRepository.findAllByUsers(userByToken));
    }
}
