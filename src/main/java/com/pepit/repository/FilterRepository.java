package com.pepit.repository;

import com.pepit.model.Filter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilterRepository extends CrudRepository<Filter, Integer> {
}
