package com.example.SomeFlower.domain.flowerShop;

import com.example.SomeFlower.config.resTemplate.ResponseException;
import com.example.SomeFlower.domain.Validatable;
import com.example.SomeFlower.domain.userGroup.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.example.SomeFlower.constant.ResponseTemplateStatus.PHONENUM_FORM_INVALID;
import static com.example.SomeFlower.constant.ServiceConstant.REGEX_SHOPNUM;

@Getter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlowerShopUpdateDto implements Validatable {

    private Long id;
    private String shopName;
    private String description;
    private String shopImage;
    private String shopLink;
    private String businessHours;
    private String businessDates;
    private String shopNumber;
    private Address address;


    @Override
    public void validate() {
        if(!REGEX_SHOPNUM.matcher(this.shopNumber).matches()){
            throw new ResponseException(PHONENUM_FORM_INVALID);
        }

    }

}
