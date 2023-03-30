package com.example.SomeFlower.domain.flowerShop;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ShopAddress {

    private String city;
    private String street;
    private String zipcode;
    private String details;
}
