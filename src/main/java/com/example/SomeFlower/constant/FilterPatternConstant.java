package com.example.SomeFlower.constant;

public class FilterPatternConstant {

    //필터 제외할 url 모음
    public static String[] pathArray = new String[]{
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger*/**",
            "/swagger-ui/index.html",

            "/api/member/join**",
            "/api/member/login**",
            "/api/seller/join**",
            "/api/seller/login**",
            "/upload/*"
    };
}
