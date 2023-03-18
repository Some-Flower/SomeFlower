package com.example.SomeFlower.util.filter;

import com.example.SomeFlower.config.resTemplate.ResponseTemplate;
import com.example.SomeFlower.constant.ResponseTemplateStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtExceptionFilter extends OncePerRequestFilter {
    public final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("jwtExceptionFilter 실행");
        try{
            filterChain.doFilter(request,response);
        }
        catch (RuntimeException e){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("UTF-8");
            ResponseTemplateStatus errorDetail = ResponseTemplateStatus.findByHttpStatus(HttpStatus.valueOf(e.getMessage()));
            objectMapper.writeValue(response.getWriter(), ResponseTemplate.of(errorDetail, e.getMessage()));
        }
    }
}
