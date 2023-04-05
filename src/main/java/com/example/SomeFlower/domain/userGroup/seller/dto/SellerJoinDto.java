package com.example.SomeFlower.domain.userGroup.seller.dto;

import com.example.SomeFlower.config.resTemplate.ResponseException;
import com.example.SomeFlower.constant.ResponseTemplateStatus;
import com.example.SomeFlower.domain.Validatable;
import com.example.SomeFlower.domain.flowerShop.FlowerShop;
import com.example.SomeFlower.domain.flowerShop.FlowerShopDto;
import com.example.SomeFlower.domain.userGroup.Address;
import com.example.SomeFlower.domain.userGroup.Role;
import com.example.SomeFlower.domain.userGroup.Status;
import com.example.SomeFlower.domain.userGroup.seller.Seller;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

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
    private List<FlowerShopDto> flowerShops;
    private Role role;
    private Status status;

    public Seller asEntity(){
        Seller seller = new Seller();
        BeanUtils.copyProperties(this, seller);
        seller.setRole(Role.SELLER);
        seller.setStatus(Status.ACTIVE);
        return seller;
    }

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

//    @AllArgsConstructor
//    @NoArgsConstructor
//    @Getter @Builder
//    public static class LoginDto{
//
//        @NotBlank
//        private String email;
//        @NotBlank
//        private String pwd;
//
//        @Override
//        public void validate() {
//            if (!REGEX_EMAIL.matcher(this.email).matches()) {
//                throw new ResponseException(ResponseTemplateStatus.EMAIL_FORM_INVALID);
//            }
//        }
//    }
//
//    @AllArgsConstructor
//    @NoArgsConstructor
//    @Getter @Builder
//    public static class UpdateDto implements Validatable{
//        private String email;
//        private String pwd;
//        private String name;
//        private String phoneNumber;
//        private String profileImage;
//        private List<FlowerShop> flowerShops;
//
//        @Override
//        public void validate() {
//            if(!REGEX_PHONENUM.matcher(this.phoneNumber).matches()){
//                throw new ResponseException(PHONENUM_FORM_INVALID);
//            }
//        }
//    }
//
//    @AllArgsConstructor
//    @NoArgsConstructor
//    @Getter @Builder
//    public static class WithdrawDto{
//        private String pwd;
//
//    }

