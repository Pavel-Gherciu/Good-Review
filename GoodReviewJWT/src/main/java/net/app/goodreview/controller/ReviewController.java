package net.app.goodreview.controller;

import net.app.goodreview.dto.request.ReviewDto;
import net.app.goodreview.dto.responses.ReviewResponseDto;
import net.app.goodreview.model.Review;
import net.app.goodreview.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static net.app.goodreview.service.mapper.ReviewMapper.*;

@RestController
@CrossOrigin
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<ReviewResponseDto> addReview(@RequestBody ReviewDto reviewDto){
        Review reviewSaved = reviewService
                .save(toReview(reviewDto), reviewDto.getUserId(), reviewDto.getCompanyId());
        return ResponseEntity.status(HttpStatus.CREATED).body(fromReview(reviewSaved));
    }

    @GetMapping("/readall/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewResponseDto> getAllCompaniesByUser(@PathVariable final Long userId) {
        return fromReviewList(reviewService.getReviewListByUser(userId));
    }

    @GetMapping("/readall/company/{companyId}")
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewResponseDto> getAllCompaniesByCategory(@PathVariable final Integer companyId) {
        return fromReviewList(reviewService.getReviewListByCompany(companyId));
    }

    @GetMapping("/readall")
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewResponseDto> getAllCompanies() {
        return fromReviewList(reviewService.getReviewList());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReview(@PathVariable Integer id) {
        reviewService.deleteReview(id);
    }
}
