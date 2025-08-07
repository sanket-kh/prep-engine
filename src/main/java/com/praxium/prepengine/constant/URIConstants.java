package com.praxium.prepengine.constant;

public class URIConstants {
    public static final String[] PUBLIC_ACCESS_URI = new String[]{
            "/user/login",
            "/",
            "/v3/swagger-resources",
            "/swagger-ui/**",
            "/webjars/**"};
    public static final String[] USER_ACCESS_URI = new String[]{
            "/api/v1/auth/user/authentication",
            "/api/v1/auth/user/register",
            "/v3/api-docs",
            "/v3/swagger-resources",
            "/swagger-ui/**",
            "/webjars/**"}; ;
}

