package net.app.goodreview.service.mapper;

import net.app.goodreview.dto.request.NewCompaniesDto;
import net.app.goodreview.dto.request.UpdateCompaniesDto;
import net.app.goodreview.dto.responses.CompaniesResponseDto;
import net.app.goodreview.model.Companies;

import java.util.ArrayList;
import java.util.List;

public class CompaniesMapper {
    public static Companies toCompanies(NewCompaniesDto companyDTO) {
        return Companies.builder()
                .title(companyDTO.getTitle())
                .about(companyDTO.getAbout())
                .logo(companyDTO.getLogo())
                .city(companyDTO.getCity())
                .location(companyDTO.getLocation())
                .site(companyDTO.getSite())
                .route(companyDTO.getRoute())
                .schedule(companyDTO.getSchedule())
                .topImg(companyDTO.getTopImg())
                .siteLink(companyDTO.getSiteLink())
                .phone(companyDTO.getPhone())
                .build();
    }

    public static Companies toCompanies(UpdateCompaniesDto companyDTO) {
        return Companies.builder()
                .title(companyDTO.getTitle())
                .about(companyDTO.getAbout())
                .logo(companyDTO.getLogo())
                .city(companyDTO.getCity())
                .location(companyDTO.getLocation())
                .site(companyDTO.getSite())
                .route(companyDTO.getRoute())
                .schedule(companyDTO.getSchedule())
                .topImg(companyDTO.getTopImg())
                .siteLink(companyDTO.getSiteLink())
                .phone(companyDTO.getPhone())
                .build();
    }

    public static CompaniesResponseDto fromCompanies(Companies company) {
        return CompaniesResponseDto.builder()
                .companyId(company.getId())
                .title(company.getTitle())
                .about(company.getAbout())
                .logo(company.getLogo())
                .city(company.getCity())
                .location(company.getLocation())
                .site(company.getSite())
                .route(company.getRoute())
                .schedule(company.getSchedule())
                .topImg(company.getTopImg())
                .siteLink(company.getSiteLink())
                .phone(company.getPhone())
                .categoryId(company.getCategory().getId())
                .build();
    }

    public static List<Companies> toCompaniesList(List<NewCompaniesDto> newCompaniesDtoList) {
        List<Companies> companiesList = new ArrayList<>();
        newCompaniesDtoList.forEach(
                companyDto -> companiesList.add(toCompanies(companyDto)));
        return companiesList;
    }

    public static List<CompaniesResponseDto> fromCompaniesList(List<Companies> companiesList) {
        List<CompaniesResponseDto> companiesDtoList = new ArrayList<>();
        companiesList.forEach(
                company -> companiesDtoList.add(fromCompanies(company)));
        return companiesDtoList;
    }
}
