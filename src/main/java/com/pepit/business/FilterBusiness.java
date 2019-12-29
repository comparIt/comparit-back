package com.pepit.business;

import com.pepit.constants.TypeAlertEnum;
import com.pepit.converters.FilterConverter;
import com.pepit.converters.UserConverter;
import com.pepit.dto.FilterCriteriaDto;
import com.pepit.dto.FilterDto;
import com.pepit.model.Filter;
import com.pepit.service.FilterService;
import com.pepit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilterBusiness {

    @Autowired
    UserService userService;

    @Autowired
    UserConverter userConverter;

    @Autowired
    FilterService filterService;

    @Autowired
    FilterConverter filterConverter;

    public FilterDto createFromCriteriaDto(FilterCriteriaDto filterCriteriaDto) {
        FilterDto filterDto = FilterDto.builder()
                .user(userConverter.entityToDto(userService.getUserByToken()))
                .alertType(Enum.valueOf(TypeAlertEnum.class, filterCriteriaDto.getAlertType()))
                .criterias(filterCriteriaDto.getCriterias())
                .isAlert(filterCriteriaDto.getIsAlert())
                .build();
        Filter filter = filterConverter.dtoToEntity(filterDto);
        return filterService.create(filter);
    }

    public List<FilterDto> getAllFiltersByUser() {
        return filterService.getAllFiltersByUser(userService.getUserByToken());
    }

}
