package com.example.SomeFlower.domain.userGroup.seller.dto;

import com.example.SomeFlower.config.resTemplate.ResponseException;
import com.example.SomeFlower.constant.ResponseTemplateStatus;
import com.example.SomeFlower.domain.Validatable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.example.SomeFlower.constant.ServiceConstant.REGEX_EMAIL;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class SellerLoginDto implements Validatable {

    @NotBlank
    private String email;
    @NotBlank
    private String pwd;

    @Override
    public void validate() {
        if (!REGEX_EMAIL.matcher(this.email).matches()) {
            throw new ResponseException(ResponseTemplateStatus.EMAIL_FORM_INVALID);
        }
    }
}
