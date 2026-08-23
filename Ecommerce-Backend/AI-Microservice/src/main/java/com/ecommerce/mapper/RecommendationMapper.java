package com.ecommerce.mapper;

import com.ecommerce.dto.response.RecommendationResponse;
import com.ecommerce.model.Recommendation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RecommendationMapper {

    private final ModelMapper modelMapper;

    public RecommendationMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public RecommendationResponse toResponse(Recommendation recommendation) {
        return modelMapper.map(recommendation, RecommendationResponse.class);
    }

}