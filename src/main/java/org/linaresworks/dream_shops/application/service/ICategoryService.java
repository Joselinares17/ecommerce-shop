package org.linaresworks.dream_shops.application.service;

import org.linaresworks.dream_shops.domain.entity.Category;

import java.util.List;

public interface ICategoryService {
    Category getCategoryById(Long id);
    Category getCategoryByName(String name);
    List<Category> getAllCategories();
    Category addCategory(Category category);
    Category updateCategory(Long id, Category category);
    void deleteCategoryById(Long id);
}
