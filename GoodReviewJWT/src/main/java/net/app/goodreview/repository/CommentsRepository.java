package net.app.goodreview.repository;

import net.app.goodreview.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments, Integer>  {

    List<Comments> getCommentsByReviewId(Integer reviewId);

    List<Comments> getCommentsByUserId(Long userId);
}
