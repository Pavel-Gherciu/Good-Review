package net.app.goodreview.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponseDto {

    private Integer reviewId;

    private String text;

    private String image;

    private Float rating;

    private Integer companyId;

    private Long userId;

    private LocalDateTime reviewDate;
}
