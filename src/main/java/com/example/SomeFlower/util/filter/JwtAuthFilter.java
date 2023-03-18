package com.example.SomeFlower.util.filter;

import com.example.SomeFlower.constant.JwtConstant;
import com.example.SomeFlower.domain.member.dto.MemberAndDtoAdapter;
import com.example.SomeFlower.domain.member.dto.MemberDto;
import com.example.SomeFlower.domain.member.repository.MemberRepository;
import com.example.SomeFlower.service.member.UserDetailsImpl;
import com.example.SomeFlower.util.AuthToken;
import com.example.SomeFlower.util.AuthTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Collections;
import static java.util.Objects.nonNull;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {

    private final AuthTokenProvider authTokenProvider;
    private final MemberRepository memberRepository;

    /**
     * 토큰의 유효성 판단
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //헤더에서 토큰 존재여부 확인 : 여기서 request를 읽는 게 잘 안되는 중
        if(authTokenProvider.isTokenExist(request)){
            // 토큰 정보 확인
            String[] token = authTokenProvider.resolveToken(request).split(" ");
            log.info(token[1]);
            // 토큰이 유효하고 springsecuritycontextholder에 유저 정보가 저장되어 있는지
            if(authTokenProvider.isTokenValid(token[1]) && !existsAuthentication()){
                // Securitycontext에 authentication 객체를 저장 -> getauthentication
                setAuthentication(token[1]);}
            else{
                // 일반 토큰이 만료 -> 리프레시 토큰 확인
                log.info("리프레쉬 토큰 확인");
                if(authTokenProvider.isRefreshTokenExist(request)){
                    String refreshToken = authTokenProvider.resolveRefreshToken(request);
                    // 리프레쉬 토큰이 유효하면
                    if(!authTokenProvider.isTokenValid(refreshToken)){
                        //토큰 재발급
                        reIssueAccessToken(refreshToken,response);
                    }
                }
            }
        } else throw new NullPointerException();
    }

    private void setAuthentication(String token) {
        SecurityContextHolder.getContext().setAuthentication(getAuthentication(token));
    }

    //jwt 토큰에서 인증 정보 조회
    private Authentication getAuthentication(String token) {
        UserDetailsImpl userDetails = UserDetailsImpl.builder()
                .id(authTokenProvider.getMemberId(token))
                .email(authTokenProvider.getMemberEmail(token))
                .role(Collections.singleton(authTokenProvider.getMemberRole(token)))
                .build();
        // usernamepasswordauthentication통과(springwebconfig)에서 먼저 등록,
        // 따로 usernamepasswordfilter 상속 불필요 여기서 userdetails, "", 권한 반환
        return new UsernamePasswordAuthenticationToken(userDetails,"",userDetails.getAuthorities());
    }


    private void reIssueAccessToken(String refreshToken, HttpServletResponse response){
        Long memberId = AuthTokenProvider.getMemberId(refreshToken);
        AuthToken authToken = authTokenProvider.createTokens(
                MemberAndDtoAdapter.entityToDto((memberRepository.findById(memberId)
                                .orElseThrow(NullPointerException::new)))
                );
        setAuthentication(authToken.getToken());
        response.setHeader(JwtConstant.HEADER_NAME,authToken.getToken());
        response.setHeader(JwtConstant.REFRESH_TOKEN_HEADER_NAME,authToken.getRefreshToken());
    }


    //Spring security 인증 객체 (중요)
    private boolean existsAuthentication() {
        return nonNull(SecurityContextHolder.getContext().getAuthentication());
    }
}
