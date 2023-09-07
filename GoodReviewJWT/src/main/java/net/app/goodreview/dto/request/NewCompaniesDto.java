package net.app.goodreview.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewCompaniesDto {

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
}
