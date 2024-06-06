package com.vakhtang.review.services;

import com.vakhtang.review.model.entities.ReviewEntity;
import com.vakhtang.review.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<ReviewEntity> getAllReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    public ReviewEntity getReviewById(Long reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);
    }

    public boolean addReview(Long companyId, ReviewEntity review) {
        if (companyId != null && review != null) {
            review.setCompanyId(companyId);
            reviewRepository.save(review);
            return true;
        }

        return false;
    }

    public boolean updateReviewById(Long reviewId, ReviewEntity updatedReview) {
        ReviewEntity review = reviewRepository.findById(reviewId).orElse(null);
        if (review != null) {
            review.setTitle(updatedReview.getTitle());
            review.setDescription(updatedReview.getDescription());
            review.setRating(updatedReview.getRating());
            review.setCompanyId(updatedReview.getCompanyId());
            reviewRepository.save(review);
            return true;
        }

        return false;
    }

    public boolean deleteReviewById(Long reviewId) {
        ReviewEntity review = reviewRepository.findById(reviewId).orElse(null);
        if (review != null) {
            reviewRepository.delete(review);
            return true;
        }
        return false;
    }
}
