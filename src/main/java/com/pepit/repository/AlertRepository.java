package com.pepit.repository;

import com.pepit.model.Alert;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertRepository extends CrudRepository<Alert,Integer> {
}
