package com.ecommerce.util;

import com.ecommerce.dto.response.PageResponse;
import org.springframework.data.domain.Page;

import java.util.function.Function;

public class PageResponseUtil {


    private PageResponseUtil() {
    }



    public static <T, R> PageResponse<R> convert(
            Page<T> page,
            Function<T, R> mapper
    ) {


        return PageResponse.<R>builder()

                .content(
                        page.getContent()
                                .stream()
                                .map(mapper)
                                .toList()
                )

                .pageNumber(
                        page.getNumber()
                )

                .pageSize(
                        page.getSize()
                )

                .totalElements(
                        page.getTotalElements()
                )

                .totalPages(
                        page.getTotalPages()
                )

                .last(
                        page.isLast()
                )

                .build();

    }

}