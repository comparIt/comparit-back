package com.pepit.service;

import com.pepit.model.Review;

import java.util.List;

public interface ReviewService {
    Review create(Review review);

    Review update(Review review);

    Review getReviewById(Integer reviewId);

    List<Review> getAllReviewByProductId(String productId);

    List<Review> getAllReviewByUserId(Integer userId);

    List<Review> getAllReview();

    Integer deleteReview(Integer reviewId);

    Double getAvgByProductId(String productId);
}
