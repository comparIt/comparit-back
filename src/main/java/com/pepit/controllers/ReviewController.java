package com.pepit.controllers;

import com.pepit.constants.Routes;
import com.pepit.model.Review;
import com.pepit.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Routes.REVIEW)
public class ReviewController {
    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PutMapping("/saveReview")
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        return ResponseEntity.status(200).body(reviewService.create(review));
    }

    @PutMapping("/updateReview")
    public ResponseEntity<Review> updateReview(@RequestBody Review review) {
        return ResponseEntity.status(200).body(reviewService.update(review));
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable("reviewId") Integer reviewId) {
        return ResponseEntity.status(200).body(reviewService.getReviewById(reviewId));
    }

    @GetMapping("/getAllReviewsForAProduct")
    public ResponseEntity<List<Review>> getAllReviewByProductId(@RequestParam("productId") String productId) {
        return ResponseEntity.status(200).body(reviewService.getAllReviewByProductId(productId));
    }

    @GetMapping("/getAllReviewsFromUser")
    public ResponseEntity<List<Review>> getAllReviewByUserId(@RequestParam("userId") Integer userId) {
        return ResponseEntity.status(200).body(reviewService.getAllReviewByUserId(userId));
    }

    @GetMapping("/")
    public ResponseEntity<List<Review>> getAllReview() {
        return ResponseEntity.status(200).body(reviewService.getAllReview());
    }

    @GetMapping("/avg/{productId}")
    public ResponseEntity<Double> getAvgByProductId(@PathVariable("productId") String productId){
        return ResponseEntity.status(200).body(reviewService.getAvgByProductId(productId));
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Integer> deleteReview(@PathVariable("reviewId") Integer reviewId) {
        return ResponseEntity.status(204).body(reviewService.deleteReview(reviewId));
    }
}
