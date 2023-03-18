package com.example.SomeFlower.domain.member.dto;

import com.example.SomeFlower.domain.member.MemberStatus;
import com.example.SomeFlower.domain.member.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

    private Long id;

    private String email;

    private String pwd;

    private String nickName;

    private String phoneNumber;

    private String profileImage;

    private Set<Role> role;

    private MemberStatus status;


    @AllArgsConstructor
    @NoArgsConstructor
    @Getter @Builder
    public static class MemberLoginDto {
        private String email;
        private String pwd;
    }

}
