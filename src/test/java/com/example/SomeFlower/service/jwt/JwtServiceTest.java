package com.example.SomeFlower.service.jwt;

import com.example.SomeFlower.domain.member.dto.MemberDto;
import com.example.SomeFlower.domain.member.dto.MemberLoginDto;
import com.example.SomeFlower.util.AuthTokenProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import static com.example.SomeFlower.constant.JwtConstant.CLAIM_EMAIL;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class JwtServiceTest {

    private String secretKey = "sdlkfjlskdfjslfdkjfsflkjfladsfsdfdsaFDSFSDFksdldsjflk13j2##rlkjodijfvgadslkgdsgfsdfmgsmdgnlaksdfj";
    private Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    private AuthTokenProvider authTokenProvider = new AuthTokenProvider();
    private Claims claims = Jwts.claims();

    @Mock
    private HttpServletRequest httpServletRequest;

    @Mock
    private HttpServletResponse httpServletResponse;

    private MemberLoginDto memberLoginDto = MemberLoginDto.builder()
            .email("test@email.com")
            .pwd("sdffssdf")
            .build();

    private MemberDto memberDto = MemberDto.builder()
            .email("test@email.com")
            .build();

    @BeforeEach
    public void setup(){
        authTokenProvider.setTokenValidTime(30*60*1000L);
        authTokenProvider.setSecretKey(secretKey);
    }

    @Test
    void 토큰정상발급(){

        //given
        claims.put(CLAIM_EMAIL, memberLoginDto.getEmail());
        Date now = new Date();
        String jwts = Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + authTokenProvider.getTokenValidTime()))
                .compact();// set Expire Time

        //when
        String token = authTokenProvider.createToken(memberDto);

        //then
        assertEquals(memberLoginDto.getEmail(),authTokenProvider.getMemberEmail(token));
    }

    @Test
    void 토큰유효성테스트(){
//        authTokenProvider.setTokenValidTime(0);
        String token = authTokenProvider.createToken(memberDto);
        Assertions.assertEquals(false,authTokenProvider.isTokenValid(token));
    }


}
