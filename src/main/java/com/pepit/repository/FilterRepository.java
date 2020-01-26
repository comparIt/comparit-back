package com.pepit.repository;

import com.pepit.constants.TypeAlertEnum;
import com.pepit.model.Filter;
import com.pepit.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilterRepository extends CrudRepository<Filter, Integer> {
    List<Filter> findAllByUsers(User user);

    @Query("SELECT f FROM Filter f INNER JOIN FETCH f.criterias WHERE f.alert = true AND f.alertType = :typeAlertEnum")
    List<Filter> findAllByAlertIsTrueAndAlertTypeEquals(TypeAlertEnum typeAlertEnum);
}
