package net.app.goodreview.service.mapper;

import net.app.goodreview.dto.request.NewCategoriesDto;
import net.app.goodreview.dto.request.NewCommentsDto;
import net.app.goodreview.dto.request.UpdateCommentsDto;
import net.app.goodreview.dto.responses.CommentResponseDto;
import net.app.goodreview.dto.responses.CompaniesResponseDto;
import net.app.goodreview.model.Comments;

import java.util.ArrayList;
import java.util.List;

public class CommentMapper {

    public static Comments toComments(NewCommentsDto commentsDto) {
        return Comments.builder()
                .text(commentsDto.getText())
                .build();
    }

    public static Comments toComments(UpdateCommentsDto commentsDto) {
        return Comments.builder()
                .text(commentsDto.getText())
                .build();
    }

    public static CommentResponseDto fromComments(Comments comments) {
        return CommentResponseDto.builder()
                .text(comments.getText())
                .userId(comments.getUser().getId())
                .reviewId((comments.getReview().getId()))
                .commentId(comments.getId())
                .commentDate(comments.getCommentDate())
                .build();
    }

    public static List<Comments> toCommentsList(List<NewCommentsDto> newCommentsDtoList) {
        List<Comments> commentsList = new ArrayList<>();
        newCommentsDtoList.forEach(
                commentDto -> commentsList.add(toComments(commentDto)));
        return commentsList;
    }

    public static List<CommentResponseDto> fromCommentsList(List<Comments> commentsList) {
        List<CommentResponseDto> commentsDtoList = new ArrayList<>();
        commentsList.forEach(
                comment -> commentsDtoList.add(fromComments(comment)));
        return commentsDtoList;
    }
}
