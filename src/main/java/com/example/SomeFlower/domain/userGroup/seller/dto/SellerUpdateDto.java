package com.example.SomeFlower.domain.userGroup.seller.dto;

import com.example.SomeFlower.config.resTemplate.ResponseException;
import com.example.SomeFlower.domain.Validatable;
import com.example.SomeFlower.domain.flowerShop.FlowerShopUpdateDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;
import static com.example.SomeFlower.constant.ResponseTemplateStatus.PHONENUM_FORM_INVALID;
import static com.example.SomeFlower.constant.ServiceConstant.*;

@Getter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class SellerUpdateDto implements Validatable {

    private String name;
    private String phoneNumber;
    private String profileImage;
    private List<FlowerShopUpdateDto> flowerShops;

    @Override
    public void validate() {
        if(!REGEX_PHONENUM.matcher(this.phoneNumber).matches()){
            throw new ResponseException(PHONENUM_FORM_INVALID);
        }

    }
}
