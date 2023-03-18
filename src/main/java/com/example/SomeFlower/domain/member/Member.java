package com.example.SomeFlower.domain.member;

import com.example.SomeFlower.domain.member.dto.MemberDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;


@Entity @Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="member")
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    private String pwd;

    private String nickName;

    private String phoneNumber;

    private String profileImage;

    @Enumerated(value = EnumType.STRING) //저장될 때 int가 아닌 string 그자체로 저장되도록
    private Set<Role> role;

    @Enumerated(value = EnumType.STRING)
    private MemberStatus status;

    public void update(MemberDto memberDto) {
        this.email = memberDto.getEmail();
        this.pwd = memberDto.getPwd();
        this.nickName = memberDto.getNickName();
        this.phoneNumber = memberDto.getPhoneNumber();
        this.profileImage = memberDto.getProfileImage();
        this.role = memberDto.getRole();
    }

    public void setStatus(MemberStatus status) { this.status = status; }

    public void setPwd(String pwd){
        this.pwd = pwd;
    }
}
