package org.linaresworks.dream_shops.application.business;

import org.linaresworks.dream_shops.application.service.ICategoryService;
import org.linaresworks.dream_shops.domain.entity.Category;
import org.linaresworks.dream_shops.domain.repository.CategoryRepository;
import org.linaresworks.dream_shops.infrastructure.exception.AlreadyExistsException;
import org.linaresworks.dream_shops.infrastructure.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryBusiness implements ICategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryBusiness(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("category not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    @Transactional
    public Category addCategory(Category category) {
        return Optional.of(category)
                .filter(c -> !categoryRepository.existsByName(c.getName()))
                .map(categoryRepository::save)
                .orElseThrow(() -> new AlreadyExistsException(category.getName() + " already exists"));
    }

    @Override
    @Transactional
    public Category updateCategory(Long id, Category category) {
        return Optional.ofNullable(getCategoryById(id))
                .map(oldCategory -> {
                    oldCategory.setName(category.getName());
                    return categoryRepository.save(oldCategory);
                })
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
