package net.app.goodreview.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriesResponseDto {

    private Integer categoryId;

    private String name;

    private String img;
}
