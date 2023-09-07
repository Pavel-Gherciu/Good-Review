package net.app.goodreview.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompaniesResponseDto {

    private Integer companyId;

    private String title;

    private String logo;

    private String topImg;

    private String city;

    private String location;

    private String site;

    private String siteLink;

    private String schedule;

    private String phone;

    private String route;

    private String about;

    private Integer categoryId;
}
