package com.ecommerce.security;

public final class UserContext {

    private UserContext() {
    }

    public static Long getCurrentUserId() {

        // TODO: Replace with authenticated user after Spring Security + JWT integration.
        return 1L;

    }

}