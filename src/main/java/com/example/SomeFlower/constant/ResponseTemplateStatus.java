package com.example.SomeFlower.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum ResponseTemplateStatus {

    SUCCESS(HttpStatus.OK,"요청 성공", 1000),

    /**
     * 3000 : memberController : 회원가입, 로그인, 탈퇴 관련 오류
     */
    WITHDRAWAL_USER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"회원탈퇴 실패",3000),
    LOGIN_USER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"로그인에 실패하였습니다",3001),
    JOIN_USER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"회원가입 실패",3002),
    EMAIL_FORM_INVALID(HttpStatus.BAD_REQUEST,"이메일 형식 에러",3003),
    PASSWORD_FORM_INVALID(HttpStatus.BAD_REQUEST,"비밀번호 형식 에러" ,3004 ),
    NICKNAME_SIZE_INVALID(HttpStatus.BAD_REQUEST,"닉네임 형식 에러",3005),
    PHONENUM_FORM_INVALID(HttpStatus.BAD_REQUEST,"휴대폰 번호 에러",3006),
    EMAIL_DUPLICATE(HttpStatus.BAD_REQUEST,"중복된 이메일입니다",3007),
    NICKNAME_DUPLICATE(HttpStatus.BAD_REQUEST,"중복된 닉네임입니다.",3008),
    PWD_FORM_INVALID(HttpStatus.BAD_REQUEST,"비밀번호 형식 오류",3009),

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

    public static ResponseTemplateStatus  findByCOde(int code){
        return Arrays.stream(values())
                .filter(responseTemplateStatus -> responseTemplateStatus.getCode()==code)
                .findFirst()
                .orElse(ResponseTemplateStatus.LOGICAL_ERROR);
    }

    public static ResponseTemplateStatus findByHttpStatus(HttpStatus httpStatus){
        return Arrays.stream(values())
                .filter(responseTemplateStatus -> responseTemplateStatus.getHttpStatus().equals(httpStatus))
                .findFirst()
                .orElse(ResponseTemplateStatus.LOGICAL_ERROR);
    }



}
