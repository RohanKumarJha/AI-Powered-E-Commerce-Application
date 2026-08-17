package com.ecommerce.security;

public final class UserContext {

    private UserContext() {
    }

    public static Long getCurrentUserId() {
        return 1L;
    }

}