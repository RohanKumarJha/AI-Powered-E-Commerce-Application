package com.ecommerce.service.factory;

import com.ecommerce.dto.request.CategoryRequest;
import com.ecommerce.exception.ResourceAlreadyExistsException;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.model.Category;
import com.ecommerce.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CategoryFactory {

    private final CategoryRepository categoryRepository;

    public Category createCategory(CategoryRequest request) {
        log.debug("Creating Category Entity.");
        if (categoryRepository.existsByNameIgnoreCase(request.getName())) {
            log.warn("Category already exists with name: {}", request.getName());
            throw new ResourceAlreadyExistsException("Category", "name", request.getName());
        }
        Category parentCategory = null;
        if (request.getParentCategoryId() != null) {
            log.debug("Fetching parent category with id: {}", request.getParentCategoryId());
            parentCategory = getCategoryById(request.getParentCategoryId());
        }
        return Category.builder()
                .name(request.getName())
                .description(request.getDescription())
                .parentCategory(parentCategory)
                .imageUrl(request.getImageUrl())
                .active(request.getActive() != null ? request.getActive() : true)
                .build();
    }

    public Category getCategoryById(Long categoryId) {
        log.debug("Fetching category entity with id: {}", categoryId);
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
    }

    public void validateCategoryNameForUpdate(Long categoryId, String name) {
        log.debug("Validating category name for update. Category id: {}", categoryId);
        categoryRepository.findByNameIgnoreCase(name)
                .ifPresent(category -> {
                    if (!category.getCategoryId().equals(categoryId)) {
                        log.warn("Category with name '{}' already exists.", name);
                        throw new ResourceAlreadyExistsException(
                                "Category",
                                "name",
                                name
                        );
                    }
                });
    }
}