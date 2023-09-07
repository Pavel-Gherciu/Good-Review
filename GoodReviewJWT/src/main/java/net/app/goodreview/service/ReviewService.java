package net.app.goodreview.service;

import net.app.goodreview.model.Review;

import java.util.List;

public interface ReviewService {
    Review save(Review review, final Long userId, final Integer companyId);

    Review updateRating(final Float newRating, final Integer reviewId);

    List<Review> getReviewList();

    List<Review> getReviewListByUser(final Long userId);

    List<Review> getReviewListByCompany(final Integer companyId);

    void deleteReview(Integer reviewId);
}
