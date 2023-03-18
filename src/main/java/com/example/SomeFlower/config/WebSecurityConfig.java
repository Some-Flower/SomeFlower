package com.example.SomeFlower.config;

import com.example.SomeFlower.constant.FilterPatternConstant;
import com.example.SomeFlower.domain.member.repository.MemberRepository;
import com.example.SomeFlower.util.AuthTokenProvider;
import com.example.SomeFlower.util.filter.JwtAuthFilter;
import com.example.SomeFlower.util.filter.JwtExceptionFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final AuthTokenProvider authTokenProvider;
    private final MemberRepository memberRepository;
    private final ObjectMapper objectMapper;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable() // 서버에 인증정보를 저장 x
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 토큰을 사용하므로 세션 인증 x
            .and() // 헤더 보안 옵션
                .headers()
                .frameOptions().sameOrigin()
            .and()
                .authorizeHttpRequests()
                .requestMatchers(FilterPatternConstant.pathArray).permitAll()
                .anyRequest().authenticated()
            .and()
                //인증 필터를 빈으로 등록하는 과정에서 username파라미터와 userpassword파라미터를 설정할 수 있다.
                .addFilterBefore(new JwtAuthFilter(authTokenProvider,memberRepository), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtExceptionFilter(objectMapper),JwtAuthFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder PasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring().requestMatchers(
                "/v3/api-docs/**",
                "/swagger-resources/**",
                "/swagger-ui/**",
                "/webjars/**",
                "/swagger*/**",
                "/swagger-ui.html")
                .requestMatchers(FilterPatternConstant.pathArray);
    }

}
