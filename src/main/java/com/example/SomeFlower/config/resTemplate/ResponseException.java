package com.example.SomeFlower.config.resTemplate;

import com.example.SomeFlower.constant.ResponseTemplateStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@AllArgsConstructor
public class ResponseException extends RuntimeException{
    private ResponseTemplateStatus responseTemplateStatus;
}
