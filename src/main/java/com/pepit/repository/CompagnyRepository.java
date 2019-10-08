package com.pepit.repository;

import com.pepit.model.Compagny;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompagnyRepository extends CrudRepository<Compagny,Integer> {
}
