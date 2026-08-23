package com.ecommerce.service.strategy;

import com.ecommerce.client.catalog.CatalogClient;
import com.ecommerce.client.catalog.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component("POPULAR")
@RequiredArgsConstructor
public class PopularRecommendationStrategy implements RecommendationStrategy {

    private final CatalogClient catalogClient;

    @Override
    public Set<Long> recommendProducts(Long userId) {
        return catalogClient.getProducts()
                .stream()
                .sorted((p1, p2) ->
                        Double.compare(p2.getRating(), p1.getRating()))
                .limit(5)
                .map(ProductResponse::getProductId)
                .collect(Collectors.toSet());
    }

}