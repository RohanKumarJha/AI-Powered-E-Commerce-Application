package com.ecommerce.mapper;

import com.ecommerce.dto.request.CategoryRequest;
import com.ecommerce.dto.response.CategoryResponse;
import com.ecommerce.model.Category;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryMapper {

    private final ModelMapper modelMapper;

    public Category toEntity(CategoryRequest request) {
        return modelMapper.map(request, Category.class);
    }

    public CategoryResponse toResponse(Category category) {
        CategoryResponse response =
                modelMapper.map(category, CategoryResponse.class);

        if (category.getParentCategoryId() != null) {
            response.setParentCategoryId(category.getParentCategoryId());
        }

        return response;
    }

    public void updateFromRequest(
            CategoryRequest request,
            Category category) {

        modelMapper.map(request, category);
    }
}