package com.example.SomeFlower.domain.flowerShop;

import com.example.SomeFlower.domain.userGroup.Address;
import com.example.SomeFlower.domain.userGroup.seller.Seller;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Getter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlowerShopDto {
    private String shopName;
    private String description;
    private String shopImage;
    private String shopLink;
    private String businessHours;
    private String businessDates;
    private String shopNumber;
    private Address address;

    public FlowerShop asEntity(){
        FlowerShop flowerShop = new FlowerShop();
        BeanUtils.copyProperties(this, flowerShop);
        return flowerShop;
    }
}
