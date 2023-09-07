package net.app.goodreview.repository;

import net.app.goodreview.model.Companies;
import net.app.goodreview.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    List<Review> getReviewsByCompanyId(Integer companyId);

    List<Review> getReviewsByUserId(Long userId);
}
