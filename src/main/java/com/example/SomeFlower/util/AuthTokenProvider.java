package com.example.SomeFlower.util;

import com.example.SomeFlower.domain.member.Role;
import com.example.SomeFlower.domain.member.dto.MemberDto;
import com.example.SomeFlower.secret.Secret;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Enumeration;

import static com.example.SomeFlower.constant.JwtConstant.*;

@Slf4j
@Component @Getter @Setter
public class AuthTokenProvider {

    private static Key key;
    private String secretKey = Secret.JWT_SECRET_KEY;

    private long tokenValidTime;
    private long refreshTokenValidTime;

    @Autowired // 토큰이 없는 경우
    public AuthTokenProvider() {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        this.tokenValidTime = 30 * 60 * 1000L; //30분
        this.refreshTokenValidTime = tokenValidTime * 2 * 24 * 7; //7일
    }
    //토큰이 있는 경우
    public AuthTokenProvider(Key key) {
        this.key = key;
        this.tokenValidTime = 30 * 60 * 1000L; //30분
        this.refreshTokenValidTime = tokenValidTime * 2 * 24 * 7; //7일
    }

    public String createToken(MemberDto memberDto){
        Claims claims = Jwts.claims();
        claims.put(CLAIM_ROLE, Role.USER);
        claims.put(CLAIM_EMAIL,memberDto.getEmail());
        claims.put(CLAIM_ID, memberDto.getId());
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims) // payload 구성
                .setIssuedAt(now) // 발행 날짜 구성
                .setExpiration(new Date(now.getTime() + tokenValidTime)) // 만료일자 구성
                .signWith(key)
                .compact();
    }

    public String createRefreshToken(MemberDto memberDto){
        Claims claims = Jwts.claims();
        claims.put(CLAIM_ID, memberDto.getId());
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + refreshTokenValidTime))
                .signWith(key)
                .compact();
    }

    public AuthToken createTokens(MemberDto memberDto){
        return AuthToken.of(createToken(memberDto), createRefreshToken(memberDto));
    }

    /**
     * 토큰에서 회원 정보 추출
     */
    public static Long getMemberId(String token){
        return getClaimProperty(token, CLAIM_ID, Long.class);
    }

    public static String getMemberEmail(String token){
        return getClaimProperty(token, CLAIM_EMAIL, String.class);
    }

    public static Role getMemberRole(String token){ return Role.valueOf(getClaimProperty(token, CLAIM_ROLE, String.class));}

    private static <T> T getClaimProperty(String token, String property, Class<T> clazz) {
        Jws<Claims> claims = getParsedClaimsJws(token);
        return claims.getBody().get(property, clazz);
    }

    private static Jws<Claims> getParsedClaimsJws(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
    }

    /**
     * 토큰의 유효성 + 만료일자 확인
     */
    public boolean isTokenValid(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(secretKey.getBytes()).build().parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());}
        //토큰 만료(서명 불일치)
        catch (ExpiredJwtException e) {log.error("Token Expired");e.printStackTrace();return false;}
        //토큰 손상
        catch (JwtException e){log.error("Token Tampered");return false; }
        //토큰 없음
        catch (NullPointerException e){ log.error("Token is null"); return false;}
    }

    /**
     * 토큰 존재 여부 확인
     */
    // Request의 Header에서 token 값을 가져온다. "Authorization" : "TOKEN값'
    public String resolveToken(HttpServletRequest request){
        String token = request.getHeader(HEADER_NAME);
        if(token == null) throw new NullPointerException();
        return request.getHeader("Authorization");
    }

    public String resolveRefreshToken(HttpServletRequest request){
        if(isRefreshTokenExist(request)){
            return request.getHeader(REFRESH_TOKEN_HEADER_NAME);
        }
        return "u are failed";
    }

    public boolean isTokenExist(HttpServletRequest request) {
        return isTokenExist(request, HEADER_NAME);
    }

    private boolean isTokenExist(HttpServletRequest request, String name){
        return StringUtils.hasText(request.getHeader(name));
    }

    public boolean isRefreshTokenExist(HttpServletRequest request){
        return isTokenExist(request,REFRESH_TOKEN_HEADER_NAME);
    }

    public Key getKey() {
        return this.key;
    }

}
