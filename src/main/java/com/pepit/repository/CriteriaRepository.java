package com.pepit.repository;

import com.pepit.model.Criteria;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CriteriaRepository extends CrudRepository<Criteria,Integer> {
}
