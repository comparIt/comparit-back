package com.pepit.service;

import com.pepit.model.Model;

import java.util.List;

public interface ModelService {
    Model save(Model modelTosave);

    Iterable<Model> saveAll(List<Model> modelList);
}
