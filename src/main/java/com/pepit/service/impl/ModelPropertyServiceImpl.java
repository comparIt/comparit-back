package com.pepit.service.impl;

import com.pepit.exception.NoResultException;
import com.pepit.model.ModelProperty;
import com.pepit.repository.ModelPropertyRepository;
import com.pepit.service.ModelPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ModelPropertyServiceImpl implements ModelPropertyService {

    private ModelPropertyRepository modelPropertyRepository;

    @Autowired
    public ModelPropertyServiceImpl(ModelPropertyRepository modelPropertyRepository) {
        this.modelPropertyRepository = modelPropertyRepository;
    }

    @Override
    public ModelProperty save(ModelProperty modelProperty) {
        return modelPropertyRepository.save(modelProperty);
    }

    @Override
    public ModelProperty getById(Integer id) {
        return modelPropertyRepository.findById(id).orElseThrow(NoResultException::new);
    }

    @Override
    public List<ModelProperty> saveAll(List<ModelProperty> modelProperties) {
        List<ModelProperty> listModel = new ArrayList<>();
        Iterable<ModelProperty> iterModel =  modelPropertyRepository.saveAll(modelProperties);
        iterModel.forEach(listModel::add);
        return listModel;
    }
}
