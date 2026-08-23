package com.ecommerce.service.adapter;

import java.util.List;

public interface AIProviderAdapter {

    String chat(Long userId, String message);

    String summarizeReviews(List<String> reviews);

}