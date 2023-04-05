package com.example.SomeFlower.domain.userGroup.seller.dto;

import com.example.SomeFlower.domain.userGroup.Role;
import com.example.SomeFlower.domain.userGroup.Status;
import com.example.SomeFlower.domain.userGroup.seller.Seller;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SellerAndDtoAdapter {
//    public static SellerJoinDtoDto entityToDto(Seller seller){
//        return SellerDto.builder()
//                .id(seller.getId())
//                .email(seller.getEmail())
//                .pwd(seller.getPwd())
//                .name(seller.getName())
//                .phoneNumber(seller.getPhoneNumber())
//                .profileImage(seller.getProfileImage())
//                .role(seller.getRole())
//                .status(seller.getStatus())
//                .build();
//    }

    public static Seller dtoToEntity(SellerJoinDto joinDto){
        Seller seller = Seller.builder()
                .email(joinDto.getEmail())
                .pwd(joinDto.getPwd())
                .name(joinDto.getName())
                .phoneNumber(joinDto.getPhoneNumber())
                .profileImage(joinDto.getProfileImage())
                .role(Role.SELLER)
                .status(Status.ACTIVE)
                .build();

        return seller;
    }

}
