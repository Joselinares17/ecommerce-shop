package org.linaresworks.dream_shops.infrastructure.model.mapper;

import org.linaresworks.dream_shops.domain.entity.Category;
import org.linaresworks.dream_shops.infrastructure.model.request.AddCategoryRequest;
import org.linaresworks.dream_shops.infrastructure.model.response.CategoryResponse;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CategoryMapper implements Function<Category, CategoryResponse> {

    @Override
    public CategoryResponse apply(Category category) {
        return new CategoryResponse(category.getName());
    }

    public Category fromResponse(CategoryResponse response) {
        return new Category(response.getName());
    }

    public Category fromAddRequest(AddCategoryRequest request) {
        return new Category(request.getName());
    }
}
