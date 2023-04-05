package com.example.SomeFlower.domain.userGroup.member.dto;

import com.example.SomeFlower.domain.userGroup.Status;
import com.example.SomeFlower.domain.userGroup.member.Member;
import com.example.SomeFlower.domain.userGroup.Role;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberAndDtoAdapter {

    public static MemberDto entityToDto(Member member){
        return MemberDto.builder()
                .id(member.getId())
                .email(member.getEmail())
                .pwd(member.getPwd())
                .nickName(member.getNickName())
                .phoneNumber(member.getPhoneNumber())
                .profileImage(member.getProfileImage())
                .address(member.getAddress())
                .role(member.getRole())
                .status(member.getStatus())
                .build();
    }


    public static Member dtoToEntity(MemberDto.JoinDto joinDto){
        Member member = Member.builder()
                .email(joinDto.getEmail())
                .pwd(joinDto.getPwd())
                .nickName(joinDto.getNickName())
                .phoneNumber(joinDto.getPhoneNumber())
                .profileImage(joinDto.getProfileImage())
                .address(joinDto.getAddress())
                .role(Role.USER)
                .status(Status.ACTIVE)
                .build();

        return member;
    }

}
