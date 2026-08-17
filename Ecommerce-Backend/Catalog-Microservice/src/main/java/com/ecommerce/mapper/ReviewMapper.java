package com.ecommerce.mapper;

import com.ecommerce.dto.request.ReviewRequest;
import com.ecommerce.dto.response.ReviewResponse;
import com.ecommerce.model.Review;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReviewMapper {

    private final ModelMapper modelMapper;

    public Review toEntity(ReviewRequest request) {
        return modelMapper.map(request, Review.class);
    }

    public ReviewResponse toResponse(Review review) {
        return modelMapper.map(review, ReviewResponse.class);
    }

    public void updateFromRequest(ReviewRequest request, Review review) {
        modelMapper.map(request, review);
    }
}