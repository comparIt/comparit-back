package com.pepit.repository;

import com.pepit.model.ProviderContact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderContactRepository extends CrudRepository<ProviderContact,Integer> {
}
