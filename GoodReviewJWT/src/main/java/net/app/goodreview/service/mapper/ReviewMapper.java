package net.app.goodreview.service.mapper;

import net.app.goodreview.dto.request.ReviewDto;
import net.app.goodreview.dto.responses.ReviewResponseDto;
import net.app.goodreview.model.Review;

import java.util.ArrayList;
import java.util.List;

public class ReviewMapper {
    public static Review toReview(ReviewDto reviewDto) {
        return Review.builder()
                .text(reviewDto.getText())
                .rating(reviewDto.getRating())
                .image((reviewDto.getImage()))
                .build();
    }

    public static ReviewResponseDto fromReview(Review review) {
        return ReviewResponseDto.builder()
                .reviewId(review.getId())
                .text(review.getText())
                .rating(review.getRating())
                .image(review.getImage())
                .reviewDate(review.getReviewDate())
                .userId(review.getUser().getId())
                .companyId(review.getCompany().getId())
                .build();
    }

    public static List<Review> toReviewList(List<ReviewDto> reviewDtoList) {
        List<Review> reviewList = new ArrayList<>();
        reviewDtoList.forEach(
                reviewDto -> reviewList.add(toReview(reviewDto)));
        return reviewList;
    }

    public static List<ReviewResponseDto> fromReviewList(List<Review> reviewList) {
        List<ReviewResponseDto> reviewResponseDtoList = new ArrayList<>();
        reviewList.forEach(
                category -> reviewResponseDtoList.add(fromReview(category)));
        return reviewResponseDtoList;
    }
}
