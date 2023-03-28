package com.example.SomeFlower.domain.member.dto;

import com.example.SomeFlower.domain.member.MemberStatus;
import com.example.SomeFlower.domain.member.Role;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


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

    private Role role;

    private MemberStatus status;


    @AllArgsConstructor
    @NoArgsConstructor
    @Getter @Builder
    public static class LoginDto {
        private String email;
        private String pwd;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter @Builder
    public static class UpdateDto {
        private String nickName;
        private String phoneNumber;
        private String profileImage;
    }


}
