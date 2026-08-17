package com.ecommerce.controller;

import com.ecommerce.dto.request.CategoryRequest;
import com.ecommerce.dto.response.CategoryResponse;
import com.ecommerce.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public CategoryResponse createCategory(@Valid @RequestBody CategoryRequest request) {
        log.info("Received request to create category");
        return categoryService.createCategory(request);
    }

    @PutMapping("/{categoryId}")
    public CategoryResponse updateCategory(@PathVariable Long categoryId,
                                           @Valid @RequestBody CategoryRequest request) {
        log.info("Update Category Api Called with id : {}" ,categoryId);
        return categoryService.updateCategory(categoryId, request);
    }

    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable Long categoryId) {
        log.info("Delete Category Api called for category Id : {}",categoryId);
        categoryService.deleteCategory(categoryId);
    }

    @GetMapping("/{categoryId}")
    public CategoryResponse getCategoryById(@PathVariable Long categoryId) {
        log.info("Get Category API called for categoryId: {}", categoryId);
        return categoryService.getCategoryById(categoryId);
    }

    @GetMapping
    public List<CategoryResponse> getAllCategories() {
        log.info("Get All Categories API called");
        return categoryService.getAllCategories();
    }
}