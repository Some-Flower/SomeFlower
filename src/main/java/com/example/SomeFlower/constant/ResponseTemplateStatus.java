package com.example.SomeFlower.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum ResponseTemplateStatus {

    SUCCESS(HttpStatus.OK,"요청 성공", 1000),
    LOGICAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"서버 내부 논리 에러",9000);

    private HttpStatus httpStatus;
    private final String message;
    private final int code;

    //BaseResponseStatus 에서 각 해당하는 코드를 생성자로 맵핑
    ResponseTemplateStatus(HttpStatus httpStatus, String message, int code) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }

    public static ResponseTemplateStatus findByHttpStatus(HttpStatus httpStatus){
        return Arrays.stream(values())
                .filter(responseTemplateStatus -> responseTemplateStatus.getHttpStatus().equals(httpStatus))
                .findFirst()
                .orElse(ResponseTemplateStatus.LOGICAL_ERROR);
    }

}
