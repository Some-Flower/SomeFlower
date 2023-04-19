package com.example.SomeFlower.domain.userGroup.seller.dto;

import com.example.SomeFlower.config.resTemplate.ResponseException;
import com.example.SomeFlower.domain.Validatable;
import com.example.SomeFlower.domain.flowerShop.FlowerShopJoinDto;
import com.example.SomeFlower.domain.userGroup.Role;
import com.example.SomeFlower.domain.userGroup.Status;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.example.SomeFlower.constant.ResponseTemplateStatus.*;
import static com.example.SomeFlower.constant.ResponseTemplateStatus.PHONENUM_FORM_INVALID;
import static com.example.SomeFlower.constant.ServiceConstant.*;
import static com.example.SomeFlower.constant.ServiceConstant.REGEX_PHONENUM;

@Getter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class SellerJoinDto implements Validatable{

    @NotBlank(message = "이메일은 필수 입력값입니다.")
    private String email;
    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    private String pwd;
    @NotBlank(message = "이름은 필수 입력값입니다.")
    private String name;
    @NotBlank(message = "휴대폰번호는 필수 입력값입니다.")
    private String phoneNumber;
    private String profileImage;
    private List<FlowerShopJoinDto> flowerShops;
    private Role role;
    private Status status;


    @Override
    public void validate() {
        if(!REGEX_EMAIL.matcher(this.email).matches()){
            throw new ResponseException(EMAIL_FORM_INVALID);
        }
        if(!REGEX_PWD.matcher(this.pwd).matches()){
            throw new ResponseException(PASSWORD_FORM_INVALID);
        }
        if(!REGEX_NICKNAME.matcher(this.name).matches()){
            throw new ResponseException(NICKNAME_SIZE_INVALID);
        }
        if(!REGEX_PHONENUM.matcher(this.phoneNumber).matches()){
            throw new ResponseException(PHONENUM_FORM_INVALID);
        }
    }
}

