package com.pepit.repository;

import com.pepit.model.Filter;
import com.pepit.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilterRepository extends CrudRepository<Filter, Integer> {
    List<Filter> findAllByUsers(User user);
}
