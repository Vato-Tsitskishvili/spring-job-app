package com.vakhtang.review.controllers;

import com.vakhtang.review.messaging.ReviewMessageProducer;
import com.vakhtang.review.model.entities.ReviewEntity;
import com.vakhtang.review.services.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewMessageProducer reviewMessageProducer;

    public ReviewController(ReviewService reviewService, ReviewMessageProducer reviewMessageProducer) {
        this.reviewService = reviewService;
        this.reviewMessageProducer = reviewMessageProducer;
    }

    @GetMapping
    public ResponseEntity<List<ReviewEntity>> getAllReviews(@RequestParam("companyId") Long companyId) {
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewEntity> getReviewById(@PathVariable("reviewId") Long reviewId) {
        ReviewEntity review = reviewService.getReviewById(reviewId);
        if (review != null)
            return new ResponseEntity<>(review, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<String> addReview(
            @RequestParam("companyId") Long companyId,
            @RequestBody ReviewEntity review
    ) {
        boolean isReviewSaved = reviewService.addReview(companyId, review);
        if (isReviewSaved) {
            reviewMessageProducer.sendMessage(review);
            return new ResponseEntity<>("Review added successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Review not saved", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReviewById(
            @PathVariable("reviewId") Long reviewId,
            @RequestBody ReviewEntity review
    ) {
        boolean reviewUpdated = reviewService.updateReviewById(reviewId, review);
        if (reviewUpdated)
            return new ResponseEntity<>("Review updated successfully", HttpStatus.OK);

        return new ResponseEntity<>("Review not updated", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReviewById(@PathVariable("reviewId") Long reviewId) {
        boolean reviewDeleted = reviewService.deleteReviewById(reviewId);
        if (reviewDeleted)
            return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);

        return new ResponseEntity<>("Review not deleted", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/average-rating")
    public Double getAverageRating(@RequestParam("companyId") Long companyId) {
        List<ReviewEntity> reviews = reviewService.getAllReviews(companyId);
        return reviews.stream()
                .mapToDouble(ReviewEntity::getRating)
                .average()
                .orElse(0.0);
    }
}
