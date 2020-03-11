package com.pepit.service;

import com.pepit.CompareITBackApplicationTests;
import com.pepit.exception.NoResultException;
import com.pepit.model.Review;
import com.pepit.repository.ReviewRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReviewServiceTest extends CompareITBackApplicationTests {

    @Autowired
    private ReviewService reviewService;

    @MockBean
    private ReviewRepository reviewRepository;


    private List<Review> reviewList = new ArrayList<>();

    private Optional<Review> goodReview;
    private Review badReview;

    @Before
    public void initTests() {
        initDatas();
        initMocks();
    }

    private void initMocks() {
        Mockito.when(reviewRepository.save(Mockito.any(Review.class))).thenReturn(badReview);
        Mockito.when(reviewRepository.findById(Mockito.anyInt())).thenReturn(goodReview);
        Mockito.when(reviewRepository.findAllByProductId(Mockito.anyString())).thenReturn(java.util.Optional.ofNullable(reviewList));
        Mockito.when(reviewRepository.findAllByUserId(Mockito.anyInt())).thenReturn(reviewList);
        Mockito.when(reviewRepository.findAll()).thenReturn(reviewList);
    }

    private void initDatas() {

        goodReview = Optional.ofNullable(Review.builder()
                .id(1)
                .userId(1)
                .productId("1")
                .comment("good comment")
                .rate(5.00)
                .createdAt(null)
                .updatedAt(null)
                .build());

        badReview = Review.builder()
                .id(2)
                .userId(2)
                .productId("1")
                .comment("bad comment")
                .rate(1.00)
                .createdAt(null)
                .updatedAt(null)
                .build();

        reviewList.add(goodReview.get());
        reviewList.add(badReview);
    }

    @Test
    public void createOk() {
        Assert.assertEquals(badReview, reviewService.create(badReview));
    }

    @Test(expected = Exception.class)
    public void createKO() {
        Mockito.when(reviewService.create(Mockito.any(Review.class))).thenThrow(new Exception());
        reviewService.create(badReview);
    }

    public void updateOK() {
        Review badReview2 = Review.builder()
                .id(2)
                .userId(2)
                .productId("1")
                .comment("bad bad comment")
                .rate(0.00)
                .createdAt(null)
                .updatedAt(null)
                .build();

        Mockito.when(reviewRepository.findById(Mockito.anyInt())).thenReturn(Optional.ofNullable(badReview));
        Mockito.when(reviewRepository.save(Mockito.any(Mockito.any()))).thenReturn(badReview2);
        Assert.assertEquals(badReview2, reviewService.update(badReview2));
    }

    @Test(expected = Exception.class)
    public void updateKO() {
        Mockito.when(reviewRepository.findById(Mockito.anyInt())).thenThrow(new NoResultException());
        reviewService.update(badReview);
    }

    public void getReviewByIdOK() {
        Assert.assertEquals(goodReview.get(), reviewService.getReviewById(1));
        reviewService.getReviewById(1);
    }

    @Test(expected = NoResultException.class)
    public void getReviewByIdKO() {
        Mockito.when(reviewService.getReviewById(Mockito.anyInt())).thenThrow(NoResultException.class);
        reviewService.getReviewById(1);
    }

    @Test
    public void getAllReviewByProductIdOK() {
        Assert.assertEquals(reviewList, reviewService.getAllReviewByProductId("1"));
    }

    @Test(expected = NoResultException.class)
    public void getAllReviewByProductIdKO() {
        Mockito.when(reviewService.getAllReviewByProductId(Mockito.anyString())).thenThrow(NoResultException.class);
        reviewService.getAllReviewByProductId("1");
    }

    @Test
    public void getAllReviewByUserIdOk() {
        Assert.assertEquals(reviewList, reviewService.getAllReviewByUserId(1));

    }

    @Test(expected = Exception.class)
    public void getAllReviewByUserIdKO() {
        Mockito.when(reviewService.getAllReviewByUserId(Mockito.anyInt())).thenThrow(Exception.class);
        reviewService.getAllReviewByUserId(1);
    }

    @Test
    public void getAllReview() {
        Assert.assertEquals(reviewList, reviewService.getAllReview());
    }

    @Test(expected = Exception.class)
    public void getAllReviewKO() {
        Mockito.when(reviewService.getAllReview()).thenReturn(reviewList);
    }

    @Test
    public void deleteReviewOk() {
        Assert.assertEquals(Optional.of(1), Optional.ofNullable(reviewService.deleteReview(1)));
    }

    @Test(expected = Exception.class)
    public void deleteReviewKO() {
        Mockito.when(reviewService.deleteReview(Mockito.anyInt())).thenThrow(new Exception());
        reviewService.deleteReview(1);
    }

    @Test
    public void getAvgByProductOK() {
        Assert.assertEquals(Optional.of(0.00), Optional.ofNullable(reviewService.getAvgByProductId("1")));
    }

    @Test
    public void getAvgByProductTestAvis0() {
        Assert.assertEquals(Optional.of(0.00), Optional.ofNullable(reviewService.getAvgByProductId("1")));
    }

    @Test(expected = Exception.class)
    public void getAvgByProductTestKO() {
        Mockito.when(reviewService.getAllReviewByProductId("1")).thenThrow(new Exception());
        reviewService.getAvgByProductId("1");
    }


}
