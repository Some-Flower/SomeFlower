package com.example.SomeFlower.config;

import com.example.SomeFlower.domain.userGroup.member.Member;
import com.example.SomeFlower.domain.userGroup.member.dto.MemberAdapter;
import com.example.SomeFlower.domain.userGroup.member.repository.MemberRepository;
import com.example.SomeFlower.domain.userGroup.seller.Seller;
import com.example.SomeFlower.domain.userGroup.seller.dto.SellerAdapter;
import com.example.SomeFlower.domain.userGroup.seller.repository.SellerRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final MemberRepository memberRepository;
    private final SellerRepository sellerRepository;

    @Bean // loadBYUsername userdetails 반환
    @Qualifier("memberDetailsService")
    public UserDetailsService memberDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                Member member = memberRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("user not found"));
                return new MemberAdapter(member);
            }
        };
    }

    @Bean // loadBYUsername userdetails 반환
    @Qualifier("sellerDetailsService")
    public UserDetailsService sellerDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                Seller seller = sellerRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("user not found"));
                return new SellerAdapter(seller);
            }
        };
    }

    @Bean
    public AuthenticationProvider myAuthenticationProvider(){
        DaoAuthenticationProvider memberAuthProvider = new DaoAuthenticationProvider();
            memberAuthProvider.setUserDetailsService(memberDetailsService());
            memberAuthProvider.setPasswordEncoder(passwordEncoder());
        DaoAuthenticationProvider sellerAuthProvider = new DaoAuthenticationProvider();
            sellerAuthProvider.setUserDetailsService(sellerDetailsService());
            sellerAuthProvider.setPasswordEncoder(passwordEncoder());
        // 요청 경로에 따라 인증 과정을 다르게 함
        return new AuthenticationProvider() {
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                if (isAdminRequest()) {
                    return memberAuthProvider.authenticate(authentication);
                } else {
                    return sellerAuthProvider.authenticate(authentication);
                }
            }
            @Override
            public boolean supports(Class<?> authentication) {
                return memberAuthProvider.supports(authentication) || sellerAuthProvider.supports(authentication);
            }
        };
    }

    private boolean isAdminRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String requestPath = request.getServletPath() + request.getPathInfo();
        log.debug("Admin request: {}", requestPath);
        return requestPath.startsWith("/api/member");
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

