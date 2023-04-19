package com.example.SomeFlower.domain.flowerShop;

import com.example.SomeFlower.domain.userGroup.seller.Seller;

import java.util.List;
import java.util.stream.Collectors;

public class FlowerShopAndDtoAdapter {

    public static List<FlowerShop> dtoToEntity(List<FlowerShopJoinDto> flowerShopDtoList){

        List<FlowerShop> fLowerShop = flowerShopDtoList.stream()
                .map(dto -> FlowerShop.builder()
                .shopName(dto.getShopName())
                .description(dto.getDescription())
                .shopImage(dto.getShopImage())
                .shopLink(dto.getShopLink())
                .businessHours(dto.getBusinessHours())
                .businessDates(dto.getBusinessDates())
                .shopNumber(dto.getShopNumber())
                .address(dto.getAddress())
                .build())
                .collect(Collectors.toList());

        return fLowerShop;
    }

}
