package com.example.SomeFlower.domain.userGroup.seller.dto;

import com.example.SomeFlower.domain.userGroup.Role;
import com.example.SomeFlower.domain.userGroup.Status;
import com.example.SomeFlower.domain.userGroup.seller.Seller;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

@RequiredArgsConstructor
public class SellerAndDtoAdapter {

    public static Seller dtoToEntity(SellerJoinDto joinDto){
        Seller seller = Seller.builder()
                .email(joinDto.getEmail())
                .pwd(joinDto.getPwd())
                .name(joinDto.getName())
                .phoneNumber(joinDto.getPhoneNumber())
                .profileImage(joinDto.getProfileImage())
                .flowerShops(new ArrayList<>())
                .role(Role.SELLER)
                .status(Status.ACTIVE)
                .build();

        return seller;
    }

}
