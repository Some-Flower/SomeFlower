package com.example.SomeFlower.config.resTemplate;

import com.example.SomeFlower.constant.ResponseTemplateStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
public class ResponeException extends RuntimeException{

    public ResponeException(String message) {
        super(message);
    }

}
