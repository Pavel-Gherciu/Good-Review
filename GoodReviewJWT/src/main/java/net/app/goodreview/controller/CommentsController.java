package net.app.goodreview.controller;

import lombok.RequiredArgsConstructor;
import net.app.goodreview.dto.request.NewCommentsDto;
import net.app.goodreview.dto.request.NewCompaniesDto;
import net.app.goodreview.dto.request.UpdateCommentsDto;
import net.app.goodreview.dto.request.UpdateCompaniesDto;
import net.app.goodreview.dto.responses.CommentResponseDto;
import net.app.goodreview.dto.responses.CompaniesResponseDto;
import net.app.goodreview.service.CommentsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static net.app.goodreview.service.mapper.CommentMapper.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentsController {

    private final CommentsService commentsService;
    @PostMapping("/write/{reviewId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addComment(@PathVariable final Integer reviewId, @Valid @RequestBody NewCommentsDto newCommentsDto) {
        commentsService.save(toComments(newCommentsDto), newCommentsDto.getUserId(), reviewId);
    }

    @GetMapping("/readall/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentResponseDto> getCommentsById(@PathVariable final Long userId) {
        return fromCommentsList(commentsService.getCommentsListByUser(userId));
    }

    @GetMapping("/readall/review/{reviewId}")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentResponseDto> getAllCommentsByReview(@PathVariable final Integer reviewId) {
        return fromCommentsList(commentsService.getCommentsListByReview(reviewId));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable Integer id) {
        commentsService.deleteComment(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CommentResponseDto updateComment(@PathVariable Integer id,
                                                  @Valid @RequestBody UpdateCommentsDto updateCommentDto) {
        return fromComments(commentsService.update(id, toComments(updateCommentDto), updateCommentDto.getReviewId()));
    }
}
