package com.pepit.converters;

import com.pepit.dto.AlertDto;
import com.pepit.dto.FilterCriteriaDto;
import com.pepit.model.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AlertConverter extends GenericsConverter<Alert, AlertDto>{

    @Autowired
    public FilterConverter filterConverter;

    @Override
    public AlertDto entityToDto(Alert alert) {
        return AlertDto.builder()
                .filter(filterConverter.entityToDto(alert.getFilter()))
                .listProductsId(alert.getListProductsId())
                .dateConsulted(alert.getDateConsulted())
                .isConsulted(alert.isConsulted())
                .createdAt(alert.getCreatedAt())
                .updatedAt(alert.getUpdatedAt())
                .build();

    }

    //UNUSED
    @Override
    public Alert dtoToEntity(AlertDto alertDto) {
        return null;
    }
}
