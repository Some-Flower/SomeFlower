package com.example.SomeFlower.domain.member.dto;

import com.example.SomeFlower.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

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
                .role(member.getRole())
                .status(member.getStatus())
                .build();
    }

    public static Member dtoToEntity(MemberDto memberDto){
        Member member = Member.builder()
                .email(memberDto.getEmail())
                .pwd(memberDto.getPwd())
                .nickName(memberDto.getNickName())
                .phoneNumber(memberDto.getPhoneNumber())
                .profileImage(memberDto.getProfileImage())
                .role(memberDto.getRole())
                .status(memberDto.getStatus())
                .build();

        return member;
    }

}
