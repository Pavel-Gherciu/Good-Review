package net.app.goodreview.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import net.app.goodreview.model.Review;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@AllArgsConstructor
public class ReviewDto {

    private String text;

    private String image;

    private Float rating;

    private Integer companyId;

    private Long userId;
}
