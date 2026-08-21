package com.ecommerce.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageRequestUtil {


    private PageRequestUtil() {
    }



    public static Pageable create(
            int page,
            int size
    ) {

        return PageRequest.of(
                page,
                size,
                Sort.by("createdAt")
                        .descending()
        );

    }

}