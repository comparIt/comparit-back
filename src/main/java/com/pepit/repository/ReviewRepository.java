package com.pepit.repository;

import com.pepit.model.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Integer> {

    List<Review> findAll();

    List<Review> findAllByUserId(Integer userId);

    Optional<List<Review>> findAllByProductId(String productId);
}
