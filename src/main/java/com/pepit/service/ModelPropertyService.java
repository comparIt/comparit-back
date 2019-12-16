package com.pepit.service;

import com.pepit.model.ModelProperty;

import java.util.List;

public interface ModelPropertyService {
    ModelProperty save(ModelProperty modelProperty);

    Iterable<ModelProperty> saveAll(List<ModelProperty> modelProperties);
}
