package org.linaresworks.dream_shops.application.business;

import org.linaresworks.dream_shops.application.service.ICategoryService;
import org.linaresworks.dream_shops.domain.entity.Category;
import org.linaresworks.dream_shops.domain.repository.CategoryRepository;
import org.linaresworks.dream_shops.infrastructure.exception.AlreadyExistsException;
import org.linaresworks.dream_shops.infrastructure.exception.ResourceNotFoundException;
import org.linaresworks.dream_shops.infrastructure.model.mapper.CategoryMapper;
import org.linaresworks.dream_shops.infrastructure.model.request.AddCategoryRequest;
import org.linaresworks.dream_shops.infrastructure.model.request.CategoryUpdateRequest;
import org.linaresworks.dream_shops.infrastructure.model.response.CategoryResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryBusiness implements ICategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryBusiness(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryResponse getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .map(categoryMapper)
                .orElseThrow(() -> new ResourceNotFoundException("category not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryResponse getCategoryByName(String name) {
        return categoryRepository.findByName(name)
                .map(categoryMapper)
                .orElseThrow(() -> new ResourceNotFoundException("category not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper)
                .toList();
    }

    @Override
    @Transactional
    public CategoryResponse addCategory(AddCategoryRequest request) {
    return Optional.of(request)
        .filter(c -> !categoryRepository.existsByName(c.getName()))
        .map(
            item -> {
                Category newItem = categoryMapper.fromAddRequest(item);
                categoryRepository.save(newItem);
                return categoryMapper.apply(newItem);
            })
        .orElseThrow(() -> new AlreadyExistsException(request.getName() + " already exists"));
    }

    @Override
    @Transactional
    public CategoryResponse updateCategory(Long id, CategoryUpdateRequest request) {
        return Optional.ofNullable(getCategoryById(id))
                .map(oldCategory -> {
                    oldCategory.setName(request.getName());
                    return categoryRepository.save(categoryMapper.fromResponse(oldCategory));
                })
                .map(categoryMapper)
                .orElseThrow(() -> new ResourceNotFoundException("category not found"));
    }

    @Override
    @Transactional
    public void deleteCategoryById(Long id) {
        categoryRepository.findById(id)
                .ifPresentOrElse(categoryRepository::delete,
                        () -> {
                    throw new ResourceNotFoundException("category not found");
                        });
    }
}
