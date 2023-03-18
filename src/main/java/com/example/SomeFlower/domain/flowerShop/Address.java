package com.example.SomeFlower.domain.flowerShop;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable @Getter
@AllArgsConstructor
public class Address {

    private String zipcode;
    private String streetAdr;
    private String detailAdr;

    protected Address(){}

}
