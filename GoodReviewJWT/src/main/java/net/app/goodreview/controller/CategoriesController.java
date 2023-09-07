package net.app.goodreview.controller;

import lombok.RequiredArgsConstructor;
import net.app.goodreview.dto.responses.CategoriesResponseDto;
import net.app.goodreview.service.CategoriesService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static net.app.goodreview.service.mapper.CategoriesMapper.fromCategories;
import static net.app.goodreview.service.mapper.CategoriesMapper.fromCategoriesList;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoriesController {

    private final CategoriesService categoriesService;
    @GetMapping("/read/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public CategoriesResponseDto getCompanyById(@PathVariable final Integer categoryId) {
        return fromCategories(categoriesService.getCategory(categoryId));
    }

    @GetMapping("/readall")
    @ResponseStatus(HttpStatus.OK)
    public List<CategoriesResponseDto> getAllCategories() {
        return fromCategoriesList(categoriesService.getCategoryList());
    }
}
