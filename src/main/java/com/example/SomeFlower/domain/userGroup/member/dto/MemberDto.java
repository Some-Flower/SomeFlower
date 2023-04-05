package com.example.SomeFlower.domain.userGroup.member.dto;

import com.example.SomeFlower.config.resTemplate.ResponseException;
import com.example.SomeFlower.constant.ResponseTemplateStatus;
import com.example.SomeFlower.domain.Validatable;
import com.example.SomeFlower.domain.userGroup.Address;
import com.example.SomeFlower.domain.userGroup.Status;
import com.example.SomeFlower.domain.userGroup.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.example.SomeFlower.constant.ResponseTemplateStatus.*;
import static com.example.SomeFlower.constant.ServiceConstant.*;

@Slf4j
@Getter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

    private Long id;
    private String email;
    private String pwd;
    private String nickName;
    private String phoneNumber;
    private String profileImage;
    private Address address;
    private Role role;
    private Status status;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter @Builder
    public static class JoinDto implements Validatable {

        @NotBlank(message = "이메일은 필수 입력값입니다.")
        private String email;

        @NotBlank(message = "비밀번호는 필수 입력값입니다.")
        private String pwd;

        @NotBlank(message = "닉네임은 필수 입력값입니다.")
        private String nickName;

        @NotBlank(message = "휴대폰번호는 필수 입력값입니다.")
        private String phoneNumber;

        private String profileImage;

        @NotBlank(message = "주소는 필수 입력값입니다.")
        private Address address;

        @Override
        public void validate(){
            if(!REGEX_EMAIL.matcher(this.email).matches()){
                throw new ResponseException(EMAIL_FORM_INVALID);
            }
            if(!REGEX_PWD.matcher(this.pwd).matches()){
                throw new ResponseException(PASSWORD_FORM_INVALID);
            }
            if(!REGEX_NICKNAME.matcher(this.nickName).matches()){
                throw new ResponseException(NICKNAME_SIZE_INVALID);
            }
            if(!REGEX_PHONENUM.matcher(this.phoneNumber).matches()){
                throw new ResponseException(PHONENUM_FORM_INVALID);
            }
        }
    }


    @AllArgsConstructor
    @NoArgsConstructor
    @Getter @Builder
    public static class LoginDto implements Validatable {

        @NotBlank
        private String email;
        @NotBlank
        private String pwd;

        @Override
        public void validate() {
            if (!REGEX_EMAIL.matcher(this.email).matches()) {
                log.info("login");
                throw new ResponseException(ResponseTemplateStatus.EMAIL_FORM_INVALID);
            }
        }
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter @Builder
    public static class UpdateDto implements Validatable{
        private String nickName;
        private String phoneNumber;
        private String profileImage;
        private Address address;

        @Override
        public void validate() {
            if(!REGEX_PHONENUM.matcher(this.phoneNumber).matches()){
                throw new ResponseException(PHONENUM_FORM_INVALID);
            }
            if(!REGEX_NICKNAME.matcher(this.nickName).matches()){
                throw new ResponseException(NICKNAME_SIZE_INVALID);
            }
        }
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter @Builder
    public static class WithdrawDto{
        private String pwd;

    }



}
