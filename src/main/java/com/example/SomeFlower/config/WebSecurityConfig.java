package com.example.SomeFlower.config;

import com.example.SomeFlower.config.filter.JwtAuthenticationFilter;
import com.example.SomeFlower.constant.FilterPatternConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable() // 서버에 인증정보를 저장 x
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 토큰을 사용하므로 세션 인증 x
//            .and() // 헤더 보안 옵션
//                .headers()
//                .frameOptions().sameOrigin()
                .and()
                .authorizeHttpRequests() //요청에 대한 권한 지정
                .requestMatchers(FilterPatternConstant.pathArray).permitAll()
//                .requestMatchers("/api/member/**").hasRole("USER")
                .requestMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and() //jwt 예외 처리
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
