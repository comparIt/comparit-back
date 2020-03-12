package com.pepit.repository;

import com.pepit.model.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Integer> {

    List<Review> findAll();

    List<Review> findAllByUserId(Integer userId);

    List<Review> findAllByProductId(String productId);

    Review findByUserIdAndProductId(Integer userId, String productId);
}
