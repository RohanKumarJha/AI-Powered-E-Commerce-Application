package com.ecommerce.util;

import com.ecommerce.dto.response.PageResponse;
import org.springframework.data.domain.Page;

public class PageResponseUtil {

    private PageResponseUtil() {
    }

    public static <T> PageResponse<T> create(
            Page<T> page
    ) {

        return PageResponse.<T>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .first(page.isFirst())
                .last(page.isLast())
                .hasNext(page.hasNext())
                .hasPrevious(page.hasPrevious())
                .build();

    }

}