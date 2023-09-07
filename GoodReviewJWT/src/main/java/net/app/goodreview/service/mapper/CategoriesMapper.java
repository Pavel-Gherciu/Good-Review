package net.app.goodreview.service.mapper;

import net.app.goodreview.dto.request.NewCategoriesDto;
import net.app.goodreview.dto.responses.CategoriesResponseDto;
import net.app.goodreview.model.Categories;

import java.util.ArrayList;
import java.util.List;

public class CategoriesMapper {
    public static Categories toCategories(NewCategoriesDto categoryDTO) {
        return Categories.builder()
                .name(categoryDTO.getName())
                .img(categoryDTO.getImg())
                .build();
    }

    public static CategoriesResponseDto fromCategories(Categories category) {
        return CategoriesResponseDto.builder()
                .categoryId(category.getId())
                .img(category.getImg())
                .name(category.getName())
                .build();
    }

    public static List<Categories> toCategoriesList(List<NewCategoriesDto> newCategoriesDtoList) {
        List<Categories> categoriesList = new ArrayList<>();
        newCategoriesDtoList.forEach(
                categoryDto -> categoriesList.add(toCategories(categoryDto)));
        return categoriesList;
    }

    public static List<CategoriesResponseDto> fromCategoriesList(List<Categories> categoriesList) {
        List<CategoriesResponseDto> categoriesDtoList = new ArrayList<>();
        categoriesList.forEach(
                category -> categoriesDtoList.add(fromCategories(category)));
        return categoriesDtoList;
    }
}
