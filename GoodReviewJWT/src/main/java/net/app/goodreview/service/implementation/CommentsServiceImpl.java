package net.app.goodreview.service.implementation;

import lombok.RequiredArgsConstructor;
import net.app.goodreview.exception.CategoryNotFoundException;
import net.app.goodreview.exception.CompanyNotFoundException;
import net.app.goodreview.exception.ReviewNotFoundException;
import net.app.goodreview.exception.UserNotFoundException;
import net.app.goodreview.model.Categories;
import net.app.goodreview.model.Comments;

import net.app.goodreview.model.Companies;
import net.app.goodreview.model.Review;
import net.app.goodreview.repository.CommentsRepository;
import net.app.goodreview.repository.ReviewRepository;
import net.app.goodreview.repository.UserRepository;
import net.app.goodreview.service.CommentsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentsServiceImpl implements CommentsService {

    private final ReviewRepository reviewsRepository;

    private final UserRepository userRepository;

    private final CommentsRepository commentsRepository;

    @Override
    public void save(Comments comments, final Long userId, final Integer reviewId) {
        comments.setUser(userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("User with id " + userId + " was not found")));
        comments.setReview(reviewsRepository.findById(reviewId).orElseThrow(
                () -> new CompanyNotFoundException("Review with id " + reviewId + " was not found")));
        commentsRepository.save(comments);
    }

    @Override
    public List<Comments> getCommentsListByReview(final Integer reviewId){
        return commentsRepository.getCommentsByReviewId(reviewId);
    }

    @Override
    public List<Comments> getCommentsListByUser(final Long userId){
        return commentsRepository.getCommentsByUserId(userId);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comments commentsToDelete = commentsRepository.findById(commentId).orElseThrow(() ->
                new ReviewNotFoundException("Comment with id " + commentId + " was not found"));
        commentsRepository.deleteById(commentsToDelete.getId());
    }

    @Override
    public Comments update(final Integer commentId, Comments comments, final Integer reviewId) {
        Review review = reviewsRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException("Review with id " + reviewId + "not found"));
        Comments commentToUpdate = commentsRepository.findById(commentId).orElseThrow(() ->
                new ReviewNotFoundException("Comment with id " + commentId + " was not found"));

        commentToUpdate.setText(comments.getText());
        commentToUpdate.setReview(review);

        return commentsRepository.save(commentToUpdate);
    }
}
