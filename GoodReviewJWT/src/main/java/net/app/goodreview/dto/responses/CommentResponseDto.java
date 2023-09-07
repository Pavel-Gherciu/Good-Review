package net.app.goodreview.dto.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@AllArgsConstructor
public class CommentResponseDto {

    private Integer commentId;

    private String text;

    private LocalDateTime commentDate;

    private Integer reviewId;

    private Long userId;
}
