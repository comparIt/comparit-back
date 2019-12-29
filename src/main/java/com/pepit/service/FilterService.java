package com.pepit.service;

import com.pepit.dto.FilterDto;
import com.pepit.model.Filter;
import com.pepit.model.User;

import java.util.List;

public interface FilterService {

    FilterDto create(Filter filter);

    List<Filter> getAllFiltersByUser(User userByToken);

    List<Filter> getAllFilterWithAlertActivatedAndDaily();
    List<Filter> getAllFilterWithAlertActivatedAndWeekly();
    List<Filter> getAllFilterWithAlertActivatedAndMonthly();
}
