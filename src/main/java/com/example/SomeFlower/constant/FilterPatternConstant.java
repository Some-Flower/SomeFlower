package com.example.SomeFlower.constant;

public class FilterPatternConstant {

    //필터 제외할 url 모음
    public static String[] pathArray = new String[]{
            "/member/{id:\\d+}",
            "/member/join",
            "/member/login",
            "/upload/*"
    };
}
