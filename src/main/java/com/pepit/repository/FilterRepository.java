package com.pepit.repository;

import com.pepit.model.Filter;
import com.pepit.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilterRepository extends CrudRepository<Filter, Integer> {
    List<Filter> findAllByUsers(User user);

    @Query("SELECT f FROM Filter f WHERE f.alert = true AND f.alertType = com.pepit.constants.TypeAlertEnum.QUOTIDIENNE")
    List<Filter> findAllByIsAlertIsTrueAndDaily();

    @Query("SELECT f FROM Filter f WHERE f.alert = true AND f.alertType = com.pepit.constants.TypeAlertEnum.HEBDOMADAIRE")
    List<Filter> findAllByIsAlertIsTrueAndWeekly();

    @Query("SELECT f FROM Filter f WHERE f.alert = true AND f.alertType = com.pepit.constants.TypeAlertEnum.MENSUELLE")
    List<Filter> findAllByIsAlertIsTrueAndMonthly();
}
