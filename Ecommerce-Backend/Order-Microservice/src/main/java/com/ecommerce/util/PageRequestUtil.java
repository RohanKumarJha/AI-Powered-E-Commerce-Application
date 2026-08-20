package com.ecommerce.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public final class PageRequestUtil {

    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_SIZE = 10;
    private static final int MAX_SIZE = 100;

    private PageRequestUtil() {
    }

    public static Pageable createPageRequest(
            Integer page,
            Integer size,
            String sortBy,
            String direction
    ) {

        int pageNumber = (page == null) || page < 0
                ? DEFAULT_PAGE
                : page;

        int pageSize = (size == null) || size <= 0
                ? DEFAULT_SIZE
                : Math.min(size, MAX_SIZE);

        String property = (sortBy == null || sortBy.isBlank())
                ? "createdAt"
                : sortBy;

        Sort.Direction sortDirection =
                "desc".equalsIgnoreCase(direction)
                        ? Sort.Direction.DESC
                        : Sort.Direction.ASC;

        return PageRequest.of(
                pageNumber,
                pageSize,
                Sort.by(sortDirection, property)
        );
    }

}