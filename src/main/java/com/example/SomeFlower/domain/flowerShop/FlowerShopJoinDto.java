package com.example.SomeFlower.domain.flowerShop;

import com.example.SomeFlower.config.resTemplate.ResponseException;
import com.example.SomeFlower.domain.Validatable;
import com.example.SomeFlower.domain.userGroup.Address;
import com.example.SomeFlower.domain.userGroup.seller.Seller;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import static com.example.SomeFlower.constant.ResponseTemplateStatus.PHONENUM_FORM_INVALID;
import static com.example.SomeFlower.constant.ServiceConstant.REGEX_SHOPNUM;

@Getter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlowerShopJoinDto implements Validatable {

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
