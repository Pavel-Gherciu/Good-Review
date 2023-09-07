package net.app.goodreview.service;

import net.app.goodreview.model.Comments;

import java.util.List;

public interface CommentsService {

    Comments update(final Integer commentId, Comments comments, final Integer reviewId);

    void deleteComment(Integer commentId);

    List<Comments> getCommentsListByUser(final Long userId);

    List<Comments> getCommentsListByReview(final Integer reviewId);

    void save(Comments comments, final Long userId, final Integer reviewId);

}
