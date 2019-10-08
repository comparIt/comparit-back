package com.pepit.repository;

import com.pepit.model.ModelProperty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelPropertyRepository extends CrudRepository<ModelProperty,Integer> {
}
