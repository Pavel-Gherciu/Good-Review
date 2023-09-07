package net.app.goodreview.service;

import net.app.goodreview.model.Categories;

import java.util.List;

public interface CategoriesService {

    List<Categories> getCategoryList();

    Categories getCategory(final Integer categoryId);
}
