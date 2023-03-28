package com.example.SomeFlower.util;

import com.example.SomeFlower.constant.JwtConstant;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Slf4j
@RequiredArgsConstructor
@Getter @Builder
public class AuthToken {
    private final String token;

    public static AuthToken of(String token){
        return new AuthToken(token);
    }

    public MultiValueMap<String,String> asMultiValueMap(){
        MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
        map.add(JwtConstant.HEADER_NAME,token);
//        map.add(JwtConstant.REFRESH_TOKEN_HEADER_NAME,refreshToken);
        return map;

    }

    //헤더에 토큰 추가
    public HttpHeaders asHeaders() {return new HttpHeaders(asMultiValueMap());}

}
