package net.app.goodreview.service.implementation;

import net.app.goodreview.exception.CompanyNotFoundException;
import net.app.goodreview.exception.ReviewNotFoundException;
import net.app.goodreview.exception.UserNotFoundException;
import net.app.goodreview.model.Companies;
import net.app.goodreview.model.Review;
import net.app.goodreview.repository.CompaniesRepository;
import net.app.goodreview.repository.ReviewRepository;
import net.app.goodreview.repository.UserRepository;
import net.app.goodreview.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImplem implements ReviewService {

    private UserRepository userRepo;

    private ReviewRepository reviewRepo;

    private CompaniesRepository companyRepo;

    @Autowired
    public ReviewServiceImplem(UserRepository userRepo,
                               ReviewRepository reviewRepo,
                               CompaniesRepository companyRepo) {
        this.userRepo = userRepo;
        this.reviewRepo = reviewRepo;
        this.companyRepo = companyRepo;
    }

    @Override
    public Review save(Review review, final Long userId, final Integer companyId) {
        review.setUser(userRepo.findById(userId).orElseThrow(
                () -> new UserNotFoundException("User with id " + userId + " was not found")));
        review.setCompany(companyRepo.findById(companyId).orElseThrow(
                () -> new CompanyNotFoundException("Company with id " + companyId + " was not found")));
        return reviewRepo.save(review);
    }

    @Override
    public List<Review> getReviewListByCompany(final Integer companyId){
        return reviewRepo.getReviewsByCompanyId(companyId);
    }

    @Override
    public List<Review> getReviewListByUser(final Long userId){
        return reviewRepo.getReviewsByUserId(userId);
    }

    @Override
    public List<Review> getReviewList(){
        return reviewRepo.findAll();
    }

    @Override
    public void deleteReview(Integer reviewId) {
        Review review = reviewRepo.findById(reviewId).orElseThrow(() ->
                new ReviewNotFoundException("Review with id " + reviewId + " was not found"));
        reviewRepo.deleteById(review.getId());
    }

    //TODO:
    @Override
    public Review updateRating(final Float newRating, final Integer reviewId){
        return null;
    }
}
