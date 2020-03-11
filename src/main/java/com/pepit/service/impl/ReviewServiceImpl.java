package com.pepit.service.impl;

import com.pepit.exception.NoResultException;
import com.pepit.model.Review;
import com.pepit.repository.ReviewRepository;
import com.pepit.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review create(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Review update(Review review) {
        Review reviewToUpdate = reviewRepository.findById(review.getUserId()).orElseThrow(NoResultException::new);

        reviewToUpdate.setUserId(review.getUserId());
        reviewToUpdate.setProductId(review.getProductId());
        reviewToUpdate.setComment(review.getComment());
        reviewToUpdate.setRate(review.getRate());
        reviewToUpdate.update();
        return reviewRepository.save(reviewToUpdate);
    }

    @Override
    public Review getReviewById(Integer reviewId) {
        return reviewRepository.findById(reviewId).orElseThrow(NoResultException::new);
    }

    @Override
    public List<Review> getAllReviewByProductId(String productId) {
        return reviewRepository.findAllByProductId(productId).orElseThrow(NoResultException::new);
    }

    @Override
    public List<Review> getAllReviewByUserId(Integer userId) {
        return reviewRepository.findAllByUserId(userId);
    }

    @Override
    public List<Review> getAllReview() {
        return new ArrayList<>(reviewRepository.findAll());
    }

    @Override
    public Integer deleteReview(Integer reviewId) {
        reviewRepository.deleteById(reviewId);
        return 1;
    }

    @Override
    public Double getAvgByProductId(String productId) {
        List<Double> reviewList = getAllReviewByProductId(productId).stream().map(Review::getRate).collect(Collectors.toList());
        double averageNeedCheck = reviewList.stream().mapToDouble(val -> val).average().orElse(0.0);
        return averageNeedCheck == 0.00 ? averageNeedCheck : Math.round(averageNeedCheck * 2) / 2.0;
    }
}
