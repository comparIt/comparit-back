package com.pepit.service.impl;

import com.pepit.constants.TypeAlertEnum;
import com.pepit.converters.FilterConverter;
import com.pepit.dto.FilterDto;
import com.pepit.exception.DataProvidedException;
import com.pepit.exception.UnauthorizedException;
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
        List<Filter> filters = filterRepository.findAllByUsers(userByToken);
        return filterConverter.entityListToDtoList(filters);
    }

    @Override
    public void removeFilter(Integer id, User user) {
        Filter filter = filterRepository.findById(id).orElseThrow(DataProvidedException::new);
        if (filter.getUsers().equals(user))
            filterRepository.delete(filter);
        else
            throw new UnauthorizedException();
    }

    @Override
    public List<Filter> getAllFilterWithAlertActivatedAndMinutes() {
        return filterRepository.findAllByAlertIsTrueAndAlertTypeEquals(TypeAlertEnum.MINUTES);
    }

    @Override
    public List<Filter> getAllFilterWithAlertActivatedAndDaily() {
        return filterRepository.findAllByAlertIsTrueAndAlertTypeEquals(TypeAlertEnum.QUOTIDIENNE);
    }

    @Override
    public List<Filter> getAllFilterWithAlertActivatedAndWeekly() {
        return filterRepository.findAllByAlertIsTrueAndAlertTypeEquals(TypeAlertEnum.HEBDOMADAIRE);
    }

    @Override
    public List<Filter> getAllFilterWithAlertActivatedAndMonthly() {
        return filterRepository.findAllByAlertIsTrueAndAlertTypeEquals(TypeAlertEnum.MENSUELLE);
    }
}
