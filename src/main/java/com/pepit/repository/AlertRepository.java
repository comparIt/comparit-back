package com.pepit.repository;

import com.pepit.model.Alert;
import com.pepit.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlertRepository extends CrudRepository<Alert, Integer> {

    Optional<List<Alert>> findAllByUserAndIsConsultedIsFalse(User userByToken);

    Optional<List<Alert>> findAllByUser(User userByToken);


}
