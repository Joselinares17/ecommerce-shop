package org.linaresworks.dream_shops.application.service;

import org.linaresworks.dream_shops.infrastructure.model.request.AddCategoryRequest;
import org.linaresworks.dream_shops.infrastructure.model.request.CategoryUpdateRequest;
import org.linaresworks.dream_shops.infrastructure.model.response.CategoryResponse;

import java.util.List;

public interface ICategoryService {
    CategoryResponse getCategoryById(Long id);
    CategoryResponse getCategoryByName(String name);
    List<CategoryResponse> getAllCategories();
    CategoryResponse addCategory(AddCategoryRequest request);
    CategoryResponse updateCategory(Long id, CategoryUpdateRequest request);
    void deleteCategoryById(Long id);
}
