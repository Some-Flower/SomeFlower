package com.example.SomeFlower.controller;

import com.example.SomeFlower.config.resTemplate.ResponseException;
import com.example.SomeFlower.config.resTemplate.ResponseTemplate;
import com.example.SomeFlower.constant.ResponseTemplateStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice("com.example.SomeFlower.controller")
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class SomeFlowerControllerAdvice {

    @ExceptionHandler(ResponseException.class)
    protected ResponseEntity<ResponseTemplate<ResponseTemplateStatus>> except(ResponseException e){
        e.printStackTrace();
        log.info("Controller Advice , Exception : {}", e);
        ResponseTemplateStatus status = e.getResponseTemplateStatus();
        return ResponseEntity.status(status.getHttpStatus())
                .body(ResponseTemplate.of(status));
    }

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<ResponseTemplate<Void>> except(Exception e){
        e.printStackTrace();
        log.info("Controller Advice , Exception : {}",e);
        return ResponseEntity.badRequest()
                .body(ResponseTemplate.error(ResponseTemplateStatus.LOGICAL_ERROR));
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    protected ResponseEntity<ResponseTemplate<Void>> except(MissingRequestHeaderException e) {
        log.error("Excepted MissingRequestHeaderException ==> {}", e.getMessage());
        return ResponseEntity.badRequest()
                .body(ResponseTemplate.error(ResponseTemplateStatus.LOGICAL_ERROR));
    }


}
