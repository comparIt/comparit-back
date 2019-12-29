package com.pepit.converters;

import com.pepit.dto.FilterDto;
import com.pepit.exception.DataProvidedException;
import com.pepit.model.Filter;
import com.pepit.model.ModelProperty;
import com.pepit.service.ModelPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class FilterConverter extends GenericsConverter<Filter, FilterDto> {

    @Autowired
    ModelPropertyService modelPropertyService;

    @Autowired
    UserConverter userConverter;

    @Override
    public FilterDto entityToDto(Filter filter) {
        HashMap<Integer, String> mapForFilter = new HashMap<>();
        filter.getCriterias().forEach((key, value) -> mapForFilter.put(key.id, value));
        return FilterDto.builder()
                .user(userConverter.entityToDto(filter.getUsers()))
                .isAlert(filter.isAlert())
                .category(filter.getCategory())
                .alertType(filter.getAlertType())
                .createdAt(filter.getCreatedAt())
                .updatedAt(filter.getUpdatedAt())
                .criterias(mapForFilter)
                .alerts(filter.getAlerts())
                .build();
    }

    @Override
    public Filter dtoToEntity(FilterDto filterDto) {
        Map<ModelProperty, String> mapForFilterDto = new HashMap<>();
        if (!filterDto.getCriterias().isEmpty())
            filterDto.getCriterias().forEach((key, value) -> mapForFilterDto.put(modelPropertyService.getById(key), value));
        else
            throw new DataProvidedException();
        return Filter.builder()
                .users(userConverter.dtoToEntity(filterDto.getUser()))
                .isAlert(filterDto.isAlert())
                .category(filterDto.getCategory())
                .alertType(filterDto.getAlertType())
                .createdAt(filterDto.getCreatedAt())
                .updatedAt(filterDto.getUpdatedAt())
                .criterias(mapForFilterDto)
                .alerts(filterDto.getAlerts())
                .build();
    }
}
