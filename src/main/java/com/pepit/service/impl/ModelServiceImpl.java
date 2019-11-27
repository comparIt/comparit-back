package com.pepit.service.impl;

import com.pepit.model.Model;
import com.pepit.repository.ModelRepository;
import com.pepit.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ModelServiceImpl implements ModelService {

    private ModelRepository modelRepository;

    @Autowired
    public ModelServiceImpl(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Override
    public Model save(Model modelTosave) {
        return modelRepository.save(modelTosave);
    }

    @Override
    public List<Model> saveAll(List<Model> modelList) {
        List<Model> listModel = new ArrayList<>();
        Iterable<Model> iterModel =  modelRepository.saveAll(modelList);
        iterModel.forEach(listModel::add);
        return listModel;
    }
}
