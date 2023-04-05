package com.example.SomeFlower.constant;

import java.util.regex.Pattern;

public class ServiceConstant {

    public static final Pattern REGEX_EMAIL = Pattern.compile("^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}");
    public static final Pattern REGEX_PWD = Pattern.compile("(?=.*[0-9])(?=.*[a-z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,20}");
    public static final Pattern REGEX_PHONENUM = Pattern.compile("^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$");
    public static final Pattern REGEX_NICKNAME = Pattern.compile("^[가-힣]*$");

}
