package com.pepit.service;

import com.pepit.dto.FilterDto;
import com.pepit.model.Filter;
import com.pepit.model.User;

import java.util.List;

public interface FilterService {

    FilterDto create(Filter filter);

    List<FilterDto> getAllFiltersByUser(User userByToken);
}
