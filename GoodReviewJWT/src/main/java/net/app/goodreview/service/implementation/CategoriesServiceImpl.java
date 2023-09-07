package net.app.goodreview.service.implementation;

import lombok.RequiredArgsConstructor;
import net.app.goodreview.exception.CategoryNotFoundException;
import net.app.goodreview.model.Categories;
import net.app.goodreview.repository.CategoriesRepository;
import net.app.goodreview.service.CategoriesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriesServiceImpl implements CategoriesService {

    private final CategoriesRepository categoriesRepository;

    public List<Categories> getCategoryList(){
        return categoriesRepository.findAll();
    }

    public Categories getCategory(final Integer categoryId){
        Categories category = categoriesRepository.findById(categoryId).orElseThrow(
                () -> new CategoryNotFoundException("Category with id " + categoryId + " not found"));
        return category;
    }
}
