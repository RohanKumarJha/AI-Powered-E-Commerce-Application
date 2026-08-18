package com.ecommerce.exception;

public class ResourceAlreadyExistsException extends RuntimeException {

    public ResourceAlreadyExistsException(String resource, String field, Object value) {
        super(resource + " already exists with " + field + " : " + value);
    }

}