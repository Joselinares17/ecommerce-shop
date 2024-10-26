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
        if(category == null) return null;

        return new CategoryResponse(
                category.getId(),
                category.getName());
    }

    public Category fromResponse(CategoryResponse response) {
        if(response == null) return null;

        return new Category(
                response.getId(),
                response.getName());
    }

    public Category fromAddRequest(AddCategoryRequest request) {
        if(request == null) return null;

        return new Category(
                request.getName());
    }
}
