package com.pepit.service;

import com.pepit.model.ModelProperty;

import java.time.LocalDate;
import java.util.List;

public interface ModelPropertyService {
    ModelProperty save(ModelProperty modelProperty);

    ModelProperty getById(Integer id);

    Iterable<ModelProperty> saveAll(List<ModelProperty> modelProperties);
}
