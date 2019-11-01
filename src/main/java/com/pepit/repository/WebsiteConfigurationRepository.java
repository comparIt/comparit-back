package com.pepit.repository;

import com.pepit.model.WebsiteConfiguration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebsiteConfigurationRepository extends CrudRepository<WebsiteConfiguration, Integer> {
}
